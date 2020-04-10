package hu.renes.kotlin.injection.component

import android.content.Context
import dagger.Component
import hu.renes.kotlin.injection.module.ActivityModule
import hu.renes.kotlin.injection.qualifier.ActivityContext
import hu.renes.kotlin.injection.scope.ActivityScope
import hu.renes.kotlin.view.main.MainActivity

@ActivityScope
@Component(
    modules = arrayOf(ActivityModule::class),
    dependencies = arrayOf(ApplicationComponent::class)
)
interface ActivityComponent {

    fun inject(mainActivity: MainActivity)

    @ActivityContext
    fun context(): Context
}