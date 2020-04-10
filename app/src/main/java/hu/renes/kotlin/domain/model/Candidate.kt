package hu.renes.kotlin.domain.model

import android.net.Uri
import hu.renes.kotlin.view.main.adapter.CandidateViewModel
import hu.renes.kotlin.view.main.adapter.JobViewModel

data class Candidate(val name: String, val age: Int, val workplaces: List<Workplace>)

data class Workplace(
    val company: String,
    val logo: String,
    val start: Int,
    val end: Int,
    val title: String,
    val web: String
)

fun Candidate.toCandidateView() = CandidateViewModel(
    nameWithAge = "$name ($age)",
    jobs = workplaces
        .sortedBy { it.start }
        .map {
        JobViewModel(
            companyName = it.company,
            period = "${it.start}-${it.end}",
            title = it.title,
            logo = Uri.parse(it.logo),
            website = Uri.parse(it.web)
        )
    }
)