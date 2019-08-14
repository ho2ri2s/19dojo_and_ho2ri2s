package jp.co.cyberagent.dojo2019.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import jp.co.cyberagent.dojo2019.data.db.entity.User

@Database(entities = arrayOf(User::class), version = 1, exportSchema = false)
abstract class DojoDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao
}