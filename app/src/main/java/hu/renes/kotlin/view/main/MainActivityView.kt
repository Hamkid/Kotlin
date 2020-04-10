package hu.renes.kotlin.view.main

import com.hannesdorfmann.mosby3.mvp.MvpView
import hu.renes.kotlin.view.main.adapter.CandidateViewModel

interface MainActivityView : MvpView {
    fun onListShouldShown(candidateViewModel: CandidateViewModel)
    fun onError(message: String)
}