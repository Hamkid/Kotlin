package hu.renes.kotlin.view.base

import android.os.Bundle
import com.hannesdorfmann.mosby3.mvp.MvpActivity
import com.hannesdorfmann.mosby3.mvp.MvpPresenter
import com.hannesdorfmann.mosby3.mvp.MvpView
import hu.renes.kotlin.KotlinApplication
import hu.renes.kotlin.injection.component.ActivityComponent
import hu.renes.kotlin.injection.component.DaggerActivityComponent
import hu.renes.kotlin.injection.module.ActivityModule

abstract class BaseActivity<V : MvpView, P : MvpPresenter<V>> : MvpActivity<V, P>(), IActivityComponentProvider {

    private var activityComponent: ActivityComponent? = null

    protected abstract fun injectActivity(activityComponent: ActivityComponent)

    override fun onCreate(savedInstanceState: Bundle?) {
        injectActivity(getActivityComponent())
        super.onCreate(savedInstanceState)
    }

    fun getActivityComponent(): ActivityComponent {
        if (activityComponent == null) {
            activityComponent = DaggerActivityComponent.builder()
                .applicationComponent(KotlinApplication.get(this).getApplicationComponent())
                .activityModule(ActivityModule(this))
                .build()
        }
        return activityComponent as ActivityComponent
    }
}