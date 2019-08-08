package jp.co.cyberagent.dojo2019.data.repository

import androidx.lifecycle.LiveData
import jp.co.cyberagent.dojo2019.data.entity.User

interface UserRepository {

    fun upsertUser(user: User)

    fun getAllUsers(): LiveData<List<User>>

    fun getSearchedUsers(name: String?, githubAccount: String, twitterAccount: String?): LiveData<List<User>>

    //TODO sharedPredference

}