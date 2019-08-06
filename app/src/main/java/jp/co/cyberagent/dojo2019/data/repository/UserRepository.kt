package jp.co.cyberagent.dojo2019.data.repository

import androidx.lifecycle.LiveData
import jp.co.cyberagent.dojo2019.data.entity.User

interface UserRepository {

    fun getAllUser(): LiveData<List<User>>

    fun getSearchedUser(name: String?, githubAccount: String, twitterAccount: String?): LiveData<List<User>>
}