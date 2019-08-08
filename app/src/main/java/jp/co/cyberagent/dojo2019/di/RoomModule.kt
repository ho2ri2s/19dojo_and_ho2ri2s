package jp.co.cyberagent.dojo2019.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import jp.co.cyberagent.dojo2019.data.DojoDatabase
import jp.co.cyberagent.dojo2019.data.UserDao
import jp.co.cyberagent.dojo2019.data.repository.UserRepository
import jp.co.cyberagent.dojo2019.data.repository.UserRepositoryImpl
import javax.inject.Singleton

@Module
class RoomModule {


    @Singleton
    @Provides
    fun provideDatabase(context: Context) = Room.databaseBuilder(
        context.applicationContext,
        DojoDatabase::class.java, "cyber_tech_dojo.db"
    )
        .build()

    @Singleton
    @Provides
    fun userDao(dojoDatabase: DojoDatabase): UserDao = dojoDatabase.userDao()

    @Singleton
    @Provides
    fun userRepository(userDao: UserDao): UserRepository = UserRepositoryImpl(userDao)
}