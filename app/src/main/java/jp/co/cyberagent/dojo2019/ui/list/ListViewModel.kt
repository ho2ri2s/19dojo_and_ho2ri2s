package jp.co.cyberagent.dojo2019.ui.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import jp.co.cyberagent.dojo2019.data.entity.User
import jp.co.cyberagent.dojo2019.data.repository.UserRepository
import javax.inject.Inject
import kotlin.concurrent.thread

class ListViewModel @Inject constructor(
    val userRepository: UserRepository
) : ViewModel(){

    val userList by lazy { MutableLiveData<List<User>>() }

    fun getAllUsers(){
        //ひとまずCotoutine等使わず実装
        thread {
            userList.value = userRepository.getAllUsers().value
        }
    }

    fun getSearchedUsers(name: String?, githubAccount: String, twitterAccount: String?){
        userList.value =  userRepository.getSearchedUsers(name, githubAccount, twitterAccount).value
    }

}