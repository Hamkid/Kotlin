package hu.renes.kotlin

import android.app.Application
import android.content.Context
import com.squareup.picasso.Picasso
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class KotlinApplication : Application() {

    companion object {
        fun get(context: Context): KotlinApplication {
            return context.applicationContext as KotlinApplication
        }
    }

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        val picasso = Picasso.Builder(this)
            .loggingEnabled(BuildConfig.DEBUG)
            .indicatorsEnabled(false)
            .build()
        Picasso.setSingletonInstance(picasso)
    }
}