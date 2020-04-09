package hu.renes.kotlin.view.main

import android.content.Intent
import android.os.Bundle
import hu.renes.kotlin.R
import hu.renes.kotlin.injection.component.ActivityComponent
import hu.renes.kotlin.view.base.BaseActivity
import hu.renes.kotlin.view.second.SecondActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity<MainActivityView, MainActivityPresenter>(), MainActivityView {

    @Inject
    lateinit var mainPresenter: MainActivityPresenter

    override fun injectActivity(activityComponent: ActivityComponent) {
        getActivityComponent().inject(this)
    }

    override fun createPresenter(): MainActivityPresenter {
        return mainPresenter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        changeButton.setOnClickListener {
            presenter.textChanged()
        }

        nextButton.setOnClickListener {
            presenter.loadNextActivity()
        }
    }

    override fun onSecondActivityShouldLoad() {
        intent = Intent(this, SecondActivity::class.java)
        startActivity(intent)
    }

    override fun changeText(message: String) {
        messageTextView.text = message
    }
}
