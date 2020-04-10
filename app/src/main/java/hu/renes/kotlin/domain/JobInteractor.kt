package hu.renes.kotlin.domain

import hu.renes.kotlin.domain.model.Candidate
import hu.renes.kotlin.domain.service.JobService
import io.reactivex.Single
import javax.inject.Inject

class JobInteractor @Inject constructor(private val JobService: JobService) {

    fun getExperience(): Single<Candidate> {
        return JobService.getExperience()
    }
}