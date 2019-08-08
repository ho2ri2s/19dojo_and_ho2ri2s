package jp.co.cyberagent.dojo2019.ui.user


import androidx.lifecycle.ViewModel
import jp.co.cyberagent.dojo2019.App
import jp.co.cyberagent.dojo2019.data.repository.UserRepository
import javax.inject.Inject

class UserViewModel @Inject constructor(
    val userRepository: UserRepository,
    val appContext: App
):  ViewModel(){

    fun saveMyInfo(name: String?, githubAccount: String, twitterAccount: String?){
    }
}