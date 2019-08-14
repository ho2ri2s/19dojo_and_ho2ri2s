package jp.co.cyberagent.dojo2019.di.component


import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import jp.co.cyberagent.dojo2019.App
import jp.co.cyberagent.dojo2019.di.module.*
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AndroidSupportInjectionModule::class,
        ActivityBuilderModule::class,
        PreferencesModule::class,
        ViewModelModule::class,
        AppModule::class,
        RoomModule::class,
        ApiModule::class]
)
interface AppComponent : AndroidInjector<App> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<App>()

}