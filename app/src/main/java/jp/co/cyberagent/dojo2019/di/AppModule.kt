package jp.co.cyberagent.dojo2019.di


import dagger.Binds
import dagger.Module
import jp.co.cyberagent.dojo2019.App

@Module
abstract class AppModule {

    @Binds
    abstract fun provideBindApp(application: App): App


}