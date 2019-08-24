package jp.co.cyberagent.dojo2019.data.repository

import androidx.lifecycle.LiveData
import jp.co.cyberagent.dojo2019.data.db.entity.User

interface UserRepository {

    suspend fun upsertUser(user: User)

    suspend fun getAllUsers(): List<User>

    suspend fun getSearchedUsers(keyWord: String?): List<User>

    suspend fun deleteUser(id: Long?)

    fun readName(): String?

    fun readGithubAccount(): String

    fun readTwitterAccount(): String?

    fun writeName(name: String?)

    fun writeGithubAccount(githubAccount: String)

    fun writeTwitterAccount(twitterAccount: String?)
}