package jp.co.cyberagent.dojo2019.di

import android.app.Application
import android.content.SharedPreferences
import androidx.room.Room
import dagger.Module
import dagger.Provides
import jp.co.cyberagent.dojo2019.data.db.DojoDatabase
import jp.co.cyberagent.dojo2019.data.db.UserDao
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
    )
        .build()

    @Singleton
    @Provides
    fun userDao(dojoDatabase: DojoDatabase): UserDao = dojoDatabase.userDao()

    @Singleton
    @Provides
    fun userRepository(userDao: UserDao, sharedPreferences: SharedPreferences): UserRepository =
        UserRepositoryImpl(userDao, sharedPreferences)
}