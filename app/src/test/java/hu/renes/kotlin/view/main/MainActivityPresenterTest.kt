package hu.renes.kotlin.view.main

import android.accounts.NetworkErrorException
import hu.renes.kotlin.domain.JobInteractor
import hu.renes.kotlin.domain.ResourceInteractor
import hu.renes.kotlin.domain.model.Candidate
import hu.renes.kotlin.domain.model.Workplace
import hu.renes.kotlin.domain.model.toCandidateView
import io.reactivex.rxjava3.android.plugins.RxAndroidPlugins
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.functions.Supplier
import io.reactivex.rxjava3.internal.schedulers.ExecutorScheduler
import io.reactivex.rxjava3.plugins.RxJavaPlugins
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations
import java.util.concurrent.Callable
import java.util.concurrent.Executor
import java.util.concurrent.TimeUnit

class MainActivityPresenterTest {

    private val TEST: String = "TEST"
    private val TEST_URL: String = "https://www.apple.com"

    val resourceInteractor: ResourceInteractor = mock(ResourceInteractor::class.java)
    val jobInteractor: JobInteractor = mock(JobInteractor::class.java)
    val view: MainActivityView = mock(MainActivityView::class.java)
    val mainActivityPresenter = MainActivityPresenter(resourceInteractor, jobInteractor)

    val candidate: Candidate = Candidate(
        "Test",
        55,
        listOf(Workplace(TEST, TEST_URL, 2000, 2020, TEST, TEST_URL))
    )

    @Test
    fun test_load_list_successfully() {
        mainActivityPresenter.initView()
        Mockito.verify(view).onListShouldShown(candidate.toCandidateView())
    }

    @Test
    fun test_failed_to_load_list() {
        `when`(
            jobInteractor.getExperience()
        ).thenReturn(Single.error(NetworkErrorException()))

        mainActivityPresenter.initView()
        Mockito.verify(view).onError(TEST)
    }

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        val immediate: Scheduler =
            object : Scheduler() {
                override fun scheduleDirect(
                    run: Runnable,
                    delay: Long,
                    unit: TimeUnit
                ): Disposable {
                    return super.scheduleDirect(run, 0, unit)
                }

                override fun createWorker(): Worker {
                    return ExecutorScheduler.ExecutorWorker(
                        Executor { obj: Runnable -> obj.run() },
                        true,
                        true
                    )
                }
            }

        RxJavaPlugins.setInitIoSchedulerHandler { scheduler: Supplier<Scheduler?>? -> immediate }
        RxJavaPlugins.setInitComputationSchedulerHandler { scheduler: Supplier<Scheduler?>? -> immediate }
        RxJavaPlugins.setInitNewThreadSchedulerHandler { scheduler: Supplier<Scheduler?>? -> immediate }
        RxJavaPlugins.setInitSingleSchedulerHandler { scheduler: Supplier<Scheduler?>? -> immediate }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { scheduler: Callable<Scheduler?>? -> immediate }

        `when`(
            resourceInteractor.getStringResource(ArgumentMatchers.anyInt())
        ).thenReturn(TEST)

        `when`(
            jobInteractor.getExperience()
        ).thenReturn(Single.just(candidate))

        mainActivityPresenter.attachView(view)
    }

    @After
    fun tearDown() {
        RxJavaPlugins.reset()
        RxAndroidPlugins.reset()
    }
}