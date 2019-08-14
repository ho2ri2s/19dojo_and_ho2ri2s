package jp.co.cyberagent.dojo2019.di.module

import android.app.Application
import android.content.SharedPreferences
import androidx.room.Room
import dagger.Module
import dagger.Provides
import jp.co.cyberagent.dojo2019.data.db.DojoDatabase
import jp.co.cyberagent.dojo2019.data.db.UserDao
import jp.co.cyberagent.dojo2019.data.network.ApiService
import jp.co.cyberagent.dojo2019.data.repository.UserRepository
import jp.co.cyberagent.dojo2019.data.repository.UserRepositoryImpl
import javax.inject.Singleton

@Module
class RoomModule {


    @Singleton
    @Provides
    fun provideDatabase(application: Application) = Room.databaseBuilder(
        application.applicationContext,
        DojoDatabase::class.java, "cyber_tech_dojo.db"
    ).build()

    @Singleton
    @Provides
    fun provideUserDao(dojoDatabase: DojoDatabase): UserDao = dojoDatabase.userDao()

    @Singleton
    @Provides
    fun provideUserRepository(apiService: ApiService ,userDao: UserDao, sharedPreferences: SharedPreferences): UserRepository =
        UserRepositoryImpl(apiService ,userDao, sharedPreferences)
}