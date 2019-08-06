package jp.co.cyberagent.dojo2019.data.repository

import androidx.lifecycle.LiveData
import jp.co.cyberagent.dojo2019.data.UserDao
import jp.co.cyberagent.dojo2019.data.entity.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepositoryImpl @Inject constructor(
    private val userDao: UserDao
) : UserRepository {


    override  fun getAllUser(): LiveData<List<User>> {
        return userDao.getAllUser()
    }

    override fun getSearchedUser(name: String?, githubAccount: String, twitterAccount: String?): LiveData<List<User>> {
        return userDao.getSearchedUser(name, githubAccount, twitterAccount)
    }

    private fun upsertUser(user: User){
        userDao.upsertUser(user)
    }
}