package jp.co.cyberagent.dojo2019.di

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import jp.co.cyberagent.dojo2019.App
import javax.inject.Singleton

@Module
class PreferencesModule {

    @Singleton
    @Provides
    fun provideSharedPreferences(application: App): SharedPreferences =
        application.getSharedPreferences("MYDATA", Context.MODE_PRIVATE)
}