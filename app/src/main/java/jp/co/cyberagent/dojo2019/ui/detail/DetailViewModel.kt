package jp.co.cyberagent.dojo2019.ui.detail

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.viewModelScope
import jp.co.cyberagent.dojo2019.data.db.entity.User
import jp.co.cyberagent.dojo2019.data.repository.UserRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailViewModel @Inject constructor(
    val userRepository: UserRepository
) : ViewModel() {

    val user = MutableLiveData<User>()

    fun postUserToViewModel(newUser: User){
        Log.d("TAG", "viewmodel  newuser : ${newUser}")
        user.postValue(newUser)
        Log.d("TAG", "viewmodel  user : ${user}")

    }

    fun deleteUser(id: Long?){
        viewModelScope.launch{
            userRepository.deleteUser(id)
        }
    }

}
