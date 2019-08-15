package jp.co.cyberagent.dojo2019.ui.qrcode

import android.net.Uri
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import jp.co.cyberagent.dojo2019.data.db.entity.User
import jp.co.cyberagent.dojo2019.data.repository.UserRepository
import jp.co.cyberagent.dojo2019.util.LiveEvent
import kotlinx.coroutines.launch
import javax.inject.Inject

class QRcodeViewModel @Inject constructor(
    val userRepository: UserRepository
) : ViewModel() {

    //自分のProfile
    val name = MutableLiveData<String>()
    val githubAccount =  MutableLiveData<String>()
    val twitterAccount  =MutableLiveData<String>()
    //Dialog周り
    val dialogOK = LiveEvent<Unit>()
    val dialogCancel = LiveEvent<Unit>()


    fun upsertUser(user: User) {
        viewModelScope.launch {
            userRepository.upsertUser(user)
        }
    }

    fun getMyProfile() {
        name.value = userRepository.readName()
        githubAccount.value = userRepository.readGithubAccount()
        twitterAccount.value = userRepository.readTwitterAccount()
    }

}