package hu.renes.kotlin.injection.module

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import hu.renes.kotlin.injection.qualifier.ApplicationContext

@Module
class ApplicationModule(val app: Application) {

    @Provides
    fun providesApplication(): Application {
        return app
    }

    @Provides
    @ApplicationContext
    fun provideContext(): Context {
        return app
    }
}