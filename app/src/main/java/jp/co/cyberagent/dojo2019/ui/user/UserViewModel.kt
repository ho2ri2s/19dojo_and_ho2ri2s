package jp.co.cyberagent.dojo2019.ui.user


import android.util.Log
import androidx.databinding.Bindable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import jp.co.cyberagent.dojo2019.App
import jp.co.cyberagent.dojo2019.data.repository.UserRepository
import javax.inject.Inject

class UserViewModel @Inject constructor(
    val userRepository: UserRepository
) : ViewModel() {

    val error = MutableLiveData<String>()
    var name = MutableLiveData<String?>()
    val githubAccount = MutableLiveData<String>()
    val twitterAccount = MutableLiveData<String>()


    fun saveMyInfo() {

        userRepository.writeName(name.value)
        userRepository.writeGithubAccount(githubAccount.value!!)
        userRepository.writeTwitterAccount(twitterAccount.value)
    }


    fun getMyProfile() {
        //どのThreadでもクラッシュしない
        name.postValue(userRepository.readName())
        githubAccount.postValue(userRepository.readGithubAccount())
        twitterAccount.postValue(userRepository.readTwitterAccount())
    }


}