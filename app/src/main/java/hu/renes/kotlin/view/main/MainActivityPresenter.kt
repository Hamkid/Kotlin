package hu.renes.kotlin.view.main

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import hu.renes.kotlin.domain.ResourceInteractor
import javax.inject.Inject


class MainActivityPresenter @Inject constructor(private val resourceInteractor: ResourceInteractor) :
    MvpBasePresenter<MainActivityView>() {

    fun textChanged() {
        ifViewAttached { view -> view.changeText(resourceInteractor.test()) }
    }

    fun loadNextActivity() {
        ifViewAttached { view -> view.onSecondActivityShouldLoad() }
    }
}