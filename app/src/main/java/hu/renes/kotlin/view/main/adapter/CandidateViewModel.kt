package hu.renes.kotlin.view.main.adapter

import android.net.Uri
import hu.renes.kotlin.R
import hu.renes.kotlin.view.base.adapter.IListTypeProvider

data class CandidateViewModel(val nameWithAge: String, val jobs: List<JobViewModel>)

data class JobViewModel(
    val companyName: String,
    val logo: Uri,
    val period: String,
    val title: String,
    val website: Uri
) : IListTypeProvider {

    override fun getLayoutType(): Int {
        return R.layout.item_job
    }
}
