package hu.renes.kotlin

import android.app.Application
import android.content.Context
import com.squareup.picasso.Picasso
import hu.renes.kotlin.injection.component.ApplicationComponent
import hu.renes.kotlin.injection.component.DaggerApplicationComponent
import hu.renes.kotlin.injection.module.ApplicationModule
import hu.renes.kotlin.injection.module.NetworkModule
import timber.log.Timber

class KotlinApplication : Application() {

    private lateinit var applicationComponent: ApplicationComponent

    companion object {
        fun get(context: Context): KotlinApplication {
            return context.applicationContext as KotlinApplication
        }
    }

    override fun onCreate() {
        super.onCreate()

        applicationComponent = DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .networkModule(NetworkModule())
            .build()
        applicationComponent.inject(this)

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        val picasso = Picasso.Builder(this)
            .loggingEnabled(BuildConfig.DEBUG)
            .indicatorsEnabled(false)
            .build()
        Picasso.setSingletonInstance(picasso)
    }

    fun getApplicationComponent(): ApplicationComponent {
        return applicationComponent
    }
}