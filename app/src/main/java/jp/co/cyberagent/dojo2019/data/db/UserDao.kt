package jp.co.cyberagent.dojo2019.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import jp.co.cyberagent.dojo2019.data.db.entity.User

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertUser(user: User)

    @Query("SELECT * FROM user")
    suspend fun getAllUsers(): List<User>

    @Query("SELECT * FROM user WHERE name LIKE :keyWord OR githubAccount LIKE :keyWord OR twitterAccount LIKE :keyWord")
    suspend fun getSearchedUsers(keyWord: String?): List<User>

    @Query("DELETE FROM user WHERE id = :id")
    suspend fun deleteUser(id: Long?)
}