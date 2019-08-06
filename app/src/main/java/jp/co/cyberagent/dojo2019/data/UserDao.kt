package jp.co.cyberagent.dojo2019.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import jp.co.cyberagent.dojo2019.data.entity.User

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsertUser(user: User)

    @Query("SELECT * FROM user")
    fun getAllUser(): LiveData<List<User>>

    @Query("SELECT * FROM user WHERE name = :name OR githubAccount = :githubAccount OR twitterAccount = :twitterAccount")
    fun getSearchedUser(name: String?, githubAccount: String, twitterAccount: String?): LiveData<List<User>>
}