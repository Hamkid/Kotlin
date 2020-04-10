package hu.renes.kotlin.view.main

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import hu.renes.kotlin.R
import hu.renes.kotlin.domain.JobInteractor
import hu.renes.kotlin.domain.ResourceInteractor
import hu.renes.kotlin.domain.model.toCandidateView

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

import timber.log.Timber
import javax.inject.Inject

class MainActivityPresenter @Inject constructor(
    private val resourceInteractor: ResourceInteractor,
    private val jobInteractor: JobInteractor
) :
    MvpBasePresenter<MainActivityView>() {
    private var disposables: CompositeDisposable? = null

    fun initView() {
        disposables?.add(
            jobInteractor.getExperience()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ candidate ->
                    ifViewAttached { view -> view.onListShouldShown(candidate.toCandidateView()) }
                }, { throwable ->
                    Timber.d(throwable)
                    ifViewAttached { view -> view.onError(resourceInteractor.getStringResource(R.string.main_error)) }
                })
        )
    }

    override fun attachView(view: MainActivityView) {
        super.attachView(view)
        disposables = CompositeDisposable()
    }

    override fun detachView() {
        if (disposables != null && disposables!!.isDisposed()) {
            disposables?.dispose()
        }
        super.detachView()
    }
}