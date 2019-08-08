package jp.co.cyberagent.dojo2019.ui.qrcode

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import jp.co.cyberagent.dojo2019.data.entity.User
import jp.co.cyberagent.dojo2019.data.repository.UserRepository
import javax.inject.Inject

class QRcodeViewModel @Inject constructor(
     val userRepository: UserRepository
): ViewModel() {

    val name by lazy { MutableLiveData<String>() }
    val githubAccount by lazy { MutableLiveData<String>() }
    val twitterAccount by lazy { MutableLiveData<String>() }

    fun upsertUser(user: User){
        userRepository.upsertUser(user)
    }

    fun getMyProfile(){
        name.value = userRepository.readName()
        githubAccount.value = userRepository.readGithubAccount()
        twitterAccount.value = userRepository.readTwitterAccount()
    }

}