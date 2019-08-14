package jp.co.cyberagent.dojo2019.data.repository

import android.content.SharedPreferences
import android.util.Log
import jp.co.cyberagent.dojo2019.data.db.UserDao
import jp.co.cyberagent.dojo2019.data.db.entity.User
import jp.co.cyberagent.dojo2019.data.network.ApiService
import jp.co.cyberagent.dojo2019.di.module.MY_GITHUB_ACCOUNT
import jp.co.cyberagent.dojo2019.di.module.MY_NAME
import jp.co.cyberagent.dojo2019.di.module.MY_TWITTER_ACCOUNT
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val userDao: UserDao,
    private val sharedPreferences: SharedPreferences
) : UserRepository {


    override suspend fun upsertUser(user: User) {
        userDao.upsertUser(user)
    }

    override suspend fun getAllUsers(): List<User> = withContext(Dispatchers.IO){
        val userList = userDao.getAllUsers()
        for (user in userList) {
            if (user.githubImage == "") {
                user.githubImage = fetchGithubImage(user)
                userDao.upsertUser(user)
            }
        }
        return@withContext userDao.getAllUsers()
    }

    override suspend fun getSearchedUsers(name: String?, githubAccount: String, twitterAccount: String?): List<User> {
        return userDao.getSearchedUsers(name, githubAccount, twitterAccount)
    }


    suspend fun fetchGithubImage(user: User): String =
        apiService.fetchGithubUser(user.githubAccount).avatarUrl


    override fun readName(): String? =
        sharedPreferences.getString(MY_NAME, "")

    override fun readGithubAccount(): String =
        sharedPreferences.getString(MY_GITHUB_ACCOUNT, "")!!

    override fun readTwitterAccount(): String? =
        sharedPreferences.getString(MY_TWITTER_ACCOUNT, "")

    override fun writeName(name: String?) {
        val editor = sharedPreferences.edit()
        editor.putString(MY_NAME, name)
        editor.apply()
    }

    override fun writeGithubAccount(githubAccount: String) {
        val editor = sharedPreferences.edit()
        editor.putString(MY_GITHUB_ACCOUNT, githubAccount)
        editor.apply()
    }

    override fun writeTwitterAccount(twitterAccount: String?) {
        val editor = sharedPreferences.edit()
        editor.putString(MY_TWITTER_ACCOUNT, twitterAccount)
        editor.apply()
    }


}