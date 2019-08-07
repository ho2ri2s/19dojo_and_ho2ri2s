package jp.co.cyberagent.dojo2019.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjection
import jp.co.cyberagent.dojo2019.App
import javax.inject.Singleton

@Singleton
@Component(modules = [
    FragmentModule::class,
    ViewModelModule::class,
    AppModule::class,
    RoomModule::class]
)
interface AppComponent: AndroidInjector<App> {

    @Component.Builder
    abstract class Bilder: AndroidInjector.Builder<App>()

//    @Component.Builder
//    interface Builder {
//        @BindsInstance
//        fun application(application: Application): Builder
//
//        fun build(): AppComponent
//    }
}