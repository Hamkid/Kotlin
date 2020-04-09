package hu.renes.kotlin.injection.module

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides
import hu.renes.kotlin.domain.service.KotlinService
import hu.renes.kotlin.injection.qualifier.ApplicationContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import org.koin.android.BuildConfig
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

import hu.renes.kotlin.BuildConfig.BASE_URL


@Module
class NetworkModule {
    @Provides
    @Singleton
    fun provideWebSzigno(gson: Gson, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun providePicasso(
        @ApplicationContext context: Context,
        okHttpClient: OkHttpClient
    ): Picasso {
        return Picasso.Builder(context)
            .downloader(OkHttp3Downloader(okHttpClient))
            .loggingEnabled(BuildConfig.DEBUG)
            .indicatorsEnabled(false)
            .build()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(logger: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(logger)
            .connectTimeout(
                TIMEOUT_IN_SEC.toLong(),
                TimeUnit.SECONDS
            )
            .readTimeout(
                TIMEOUT_IN_SEC.toLong(),
                TimeUnit.SECONDS
            )
            .build()
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder().create()
    }

    @Provides
    @Singleton
    fun provideHttpLogger(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            logging.level = BODY
        }
        return logging
    }

    @Provides
    @Singleton
    fun provideKotlinService(retrofit: Retrofit): KotlinService {
        return KotlinService.Factory.createService(retrofit)
    }


    companion object {
        private const val TIMEOUT_IN_SEC = 30
    }
}
