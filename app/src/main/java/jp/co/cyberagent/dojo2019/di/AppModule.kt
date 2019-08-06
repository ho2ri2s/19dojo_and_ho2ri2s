package jp.co.cyberagent.dojo2019.di

import android.app.Application
import dagger.Binds
import dagger.Module

@Module
abstract class AppModule() {
    @Binds
    abstract fun provideBindApp(application: Application): Application
}