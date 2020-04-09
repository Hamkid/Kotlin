package hu.renes.kotlin.injection.module

import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import dagger.Module
import dagger.Provides
import hu.renes.kotlin.injection.qualifier.ActivityContext
import hu.renes.kotlin.injection.qualifier.SupportFragmentManager

@Module
class ActivityModule(private val activity: AppCompatActivity) {

    @Provides
    fun provideActivity(): Activity {
        return activity
    }

    @ActivityContext
    @Provides
    fun provideContext(): Context {
        return activity
    }

    @SupportFragmentManager
    @Provides
    fun provideFragmentManager(): FragmentManager {
        return activity.supportFragmentManager
    }
}