package jp.co.cyberagent.dojo2019.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import jp.co.cyberagent.dojo2019.data.db.entity.User
import jp.co.cyberagent.dojo2019.data.repository.UserRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class ListViewModel @Inject constructor(
    val userRepository: UserRepository
) : ViewModel() {

    val userList : LiveData<List<User>>
    get() = _userList
    private val _userList = MutableLiveData<List<User>>()


    fun getAllUsers() {
        viewModelScope.launch {
            _userList.postValue(userRepository.getAllUsers())
        }
    }

    fun getSeachedUsers(keyWord: String?){
        viewModelScope.launch {
            _userList.postValue(userRepository.getSearchedUsers(keyWord))
        }
    }

}