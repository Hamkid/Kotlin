package hu.renes.kotlin

import android.app.Application
import android.content.Context
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
    }

    fun getApplicationComponent(): ApplicationComponent {
        return applicationComponent
    }
}