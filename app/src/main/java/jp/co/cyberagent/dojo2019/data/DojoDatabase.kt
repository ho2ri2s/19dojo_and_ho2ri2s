package jp.co.cyberagent.dojo2019.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import jp.co.cyberagent.dojo2019.data.entity.User
import javax.inject.Inject

@Database(entities = arrayOf(User::class), version = 1)
abstract class DojoDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao
}