package jp.co.cyberagent.dojo2019.ui.user


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import jp.co.cyberagent.dojo2019.App
import jp.co.cyberagent.dojo2019.data.repository.UserRepository
import javax.inject.Inject

class UserViewModel @Inject constructor(
    val userRepository: UserRepository
):  ViewModel(){

    val name by lazy { MutableLiveData<String>() }
    val githubAccount by lazy { MutableLiveData<String>() }
    val twitterAccount by lazy { MutableLiveData<String>() }

    fun saveMyInfo(name: String?, githubAccount: String, twitterAccount: String?){

        userRepository.writeName(name)
        userRepository.writeGithubAccount(githubAccount)
        userRepository.writeTwitterAccount(twitterAccount)
    }

    fun getMyProfile(){
        name.value = userRepository.readName()
        githubAccount.value = userRepository.readGithubAccount()
        twitterAccount.value = userRepository.readTwitterAccount()
    }
}