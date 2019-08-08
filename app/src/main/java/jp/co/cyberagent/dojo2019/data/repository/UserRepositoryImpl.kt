package jp.co.cyberagent.dojo2019.data.repository

import androidx.lifecycle.LiveData
import jp.co.cyberagent.dojo2019.data.UserDao
import jp.co.cyberagent.dojo2019.data.entity.User
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepositoryImpl @Inject constructor(
    private val userDao: UserDao
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


}