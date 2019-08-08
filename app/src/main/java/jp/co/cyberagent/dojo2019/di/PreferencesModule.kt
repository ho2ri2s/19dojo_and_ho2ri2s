package jp.co.cyberagent.dojo2019.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import jp.co.cyberagent.dojo2019.App
import javax.inject.Singleton

const val MY_PREFERENCES = "my_preferences"
const val MY_NAME = "my_name"
const val MY_GITHUB_ACCOUNT = "my_github_account"
const val MY_TWITTER_ACCOUNT = "my_twitter_account"

@Module
class PreferencesModule {

    @Singleton
    @Provides
    fun provideSharedPreferences(application: Application): SharedPreferences =
        application.getSharedPreferences(MY_PREFERENCES, Context.MODE_PRIVATE)
}