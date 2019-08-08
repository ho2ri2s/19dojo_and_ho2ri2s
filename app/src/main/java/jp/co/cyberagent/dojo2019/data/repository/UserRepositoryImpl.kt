package jp.co.cyberagent.dojo2019.data.repository

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import jp.co.cyberagent.dojo2019.data.UserDao
import jp.co.cyberagent.dojo2019.data.entity.User
import jp.co.cyberagent.dojo2019.di.MY_GITHUB_ACCOUNT
import jp.co.cyberagent.dojo2019.di.MY_NAME
import jp.co.cyberagent.dojo2019.di.MY_TWITTER_ACCOUNT
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepositoryImpl @Inject constructor(
    private val userDao: UserDao,
    private val sharedPreferences: SharedPreferences
) : UserRepository {


    override fun upsertUser(user: User) {
        userDao.upsertUser(user)
    }

    override  fun getAllUsers(): LiveData<List<User>> {
        return userDao.getAllUsers()
    }

    override fun getSearchedUsers(name: String?, githubAccount: String, twitterAccount: String?): LiveData<List<User>> {
        return userDao.getSearchedUsers(name, githubAccount, twitterAccount)
    }

    override fun readName(): String? =
        sharedPreferences.getString(MY_NAME, "")

    override fun readGithubAccount(): String =
        sharedPreferences.getString(MY_GITHUB_ACCOUNT, "")!!

    override fun readTwitterAccount(): String? =
        sharedPreferences.getString(MY_TWITTER_ACCOUNT, "")

    override fun writeName(name: String?) {
        sharedPreferences.edit().putString(MY_NAME, name)
    }

    override fun writeGithubAccount(githubAccount: String) {
        sharedPreferences.edit().putString(MY_GITHUB_ACCOUNT, githubAccount)
    }

    override fun writeTwitterAccount(twitterAccount: String?) {
        sharedPreferences.edit().putString(MY_TWITTER_ACCOUNT, twitterAccount)
    }

}