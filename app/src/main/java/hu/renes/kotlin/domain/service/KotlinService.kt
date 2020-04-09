package hu.renes.kotlin.domain.service

import hu.renes.kotlin.domain.model.Candidate
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.http.GET

interface KotlinService {

    @GET("Sonkagyerek/9058c68baf1893e0fea4a3eb1d586f3b/raw/409717fd0c3ca1e2cc4621fe9c22425a3f0bce00/test.json")
    fun getExperience(): Single<Candidate>

    object Factory {
        fun createService(retrofit: Retrofit): KotlinService {
            return retrofit.create(KotlinService::class.java)
        }
    }
}