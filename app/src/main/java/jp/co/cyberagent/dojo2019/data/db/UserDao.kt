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

    @Query("SELECT * FROM user WHERE name = :name OR githubAccount = :githubAccount OR twitterAccount = :twitterAccount")
    suspend fun getSearchedUsers(name: String?, githubAccount: String, twitterAccount: String?): List<User>
}