package hu.renes.kotlin.view.second

import android.os.Bundle
import hu.renes.kotlin.R
import hu.renes.kotlin.injection.component.ActivityComponent
import hu.renes.kotlin.utility.ToastHelper
import hu.renes.kotlin.view.base.BaseActivity
import javax.inject.Inject

class SecondActivity : BaseActivity<SecondActivityView, SecondActivityPresenter>(), SecondActivityView {

    @Inject
    lateinit var secondActivityPresenter: SecondActivityPresenter

    @Inject
    lateinit var toast: ToastHelper

    override fun injectActivity(activityComponent: ActivityComponent) {
        getActivityComponent().inject(this)
    }

    override fun createPresenter(): SecondActivityPresenter {
        return secondActivityPresenter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        toast.makeToast("hello world")
    }
}
