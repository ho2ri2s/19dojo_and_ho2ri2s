package jp.co.cyberagent.dojo2019.ui.qrcode

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import jp.co.cyberagent.dojo2019.data.db.entity.User
import jp.co.cyberagent.dojo2019.data.repository.UserRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class QRcodeViewModel @Inject constructor(
    val userRepository: UserRepository
) : ViewModel() {

    //自分のProfile
    val name by lazy { MutableLiveData<String>() }
    val githubAccount by lazy { MutableLiveData<String>() }
    val twitterAccount by lazy { MutableLiveData<String>() }
    //Dialog周り
    val dialogOK = MutableLiveData<Unit>()
    val dialogCancel = MutableLiveData<Unit>()

    val builder: Uri.Builder = Uri.Builder()

    fun upsertUser(user: User) {
        viewModelScope.launch {
            userRepository.upsertUser(user)
        }
    }

    fun getMyProfile() {
        name.postValue(userRepository.readName())
        githubAccount.postValue(userRepository.readGithubAccount())
        twitterAccount.postValue(userRepository.readTwitterAccount())
        builder.scheme("ca-tech")
            .authority("dojo")
            .path("/share")
            .appendQueryParameter("iam", name.value)
            .appendQueryParameter("gw", githubAccount.value)
            .appendQueryParameter("tw", twitterAccount.value)
    }

}