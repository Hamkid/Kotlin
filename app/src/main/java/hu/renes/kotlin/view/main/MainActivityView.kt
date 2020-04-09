package hu.renes.kotlin.view.main

import com.hannesdorfmann.mosby3.mvp.MvpView


interface MainActivityView : MvpView {
    fun onSecondActivityShouldLoad()
    fun changeText(message: String)
}