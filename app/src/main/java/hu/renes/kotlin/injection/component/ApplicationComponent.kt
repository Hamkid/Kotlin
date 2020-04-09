package hu.renes.kotlin.injection.component

import android.content.Context
import dagger.Component
import hu.renes.kotlin.KotlinApplication
import hu.renes.kotlin.injection.module.ApplicationModule
import hu.renes.kotlin.injection.qualifier.ApplicationContext
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(ApplicationModule::class))
interface ApplicationComponent {

    fun inject(application: KotlinApplication)

    @ApplicationContext
    fun context(): Context
}