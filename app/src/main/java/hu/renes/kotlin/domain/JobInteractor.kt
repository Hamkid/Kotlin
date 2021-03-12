package hu.renes.kotlin.domain

import hu.renes.kotlin.domain.model.Candidate
import hu.renes.kotlin.domain.service.JobService
import io.reactivex.rxjava3.core.Single

import javax.inject.Inject

class JobInteractor @Inject constructor(private val jobService: JobService) {

    fun getExperience(): Single<Candidate> {
        return jobService.getExperience()
    }
}