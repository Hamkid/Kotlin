package hu.renes.kotlin.domain.service

import hu.renes.kotlin.domain.model.Candidate
import io.reactivex.rxjava3.core.Single

import retrofit2.Retrofit
import retrofit2.http.GET

interface JobService {

    @GET("Sonkagyerek/9058c68baf1893e0fea4a3eb1d586f3b/raw/df73b753d8b22a56996510a2d849b3c83958782d/test.json")
    fun getExperience(): Single<Candidate>

    object Factory {
        fun createService(retrofit: Retrofit): JobService {
            return retrofit.create(JobService::class.java)
        }
    }
}