package jp.co.cyberagent.dojo2019.ui.list

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import jp.co.cyberagent.dojo2019.data.entity.User
import jp.co.cyberagent.dojo2019.data.repository.UserRepository
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import javax.inject.Inject
import kotlin.concurrent.thread

class ListViewModel @Inject constructor(
    val userRepository: UserRepository
) : ViewModel() {

    //Roomが作ったLiveData（初めは空）を受け取っている。
    val userList by lazy {
        GlobalScope.async(start = CoroutineStart.LAZY) {
            userRepository.getAllUsers()
        }
    }

//    val searchedUserList = userRepository.getSearchedUsers(name, githubAccount, twitterAccount)


}