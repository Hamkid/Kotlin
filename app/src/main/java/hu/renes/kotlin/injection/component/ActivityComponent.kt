package hu.renes.kotlin.injection.component

import android.content.Context
import dagger.Component
import hu.renes.kotlin.injection.module.ActivityModule
import hu.renes.kotlin.injection.qualifier.ActivityContext
import hu.renes.kotlin.injection.scope.ActivityScope
import hu.renes.kotlin.view.main.MainActivity
import hu.renes.kotlin.view.second.SecondActivity

@ActivityScope
@Component(
    modules = arrayOf(ActivityModule::class),
    dependencies = arrayOf(ApplicationComponent::class)
)
interface ActivityComponent {

    fun inject(mainActivity: MainActivity)
    fun inject(secondActivity: SecondActivity)

    @ActivityContext
    fun context(): Context
}