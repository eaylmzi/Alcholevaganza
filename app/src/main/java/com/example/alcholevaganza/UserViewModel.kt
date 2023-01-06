package com.example.alcholevaganza

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.alcholevaganza.database.User
import com.example.alcholevaganza.database.UserDao
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class UserViewModel(val dao: UserDao) : ViewModel() {
    var userId = 0L
    var userName = ""
    var userSurname = ""
    var userEmail = ""
    var userPassword = ""
    var newUserPassword = ""
    var user :User? = User()


    private val _newUser = MutableLiveData<User?>()
    val newUser: LiveData<User?>
        get()= _newUser
    fun validateUser() {
        runBlocking {
            _newUser.value = dao.validateUser(userEmail,userPassword)
        }
    }
    fun getUserWithId(userNumber : Long){
        runBlocking {
            _newUser.value =dao.get(userNumber)
        }
    }
    fun updateUserPassword(userEmail:String) {
        runBlocking {
            dao.updateUserPassword(newUserPassword,userEmail)
            userPassword = newUserPassword
        }
    }
    fun deleteUser(id : Long){
        runBlocking {
            dao.deleteUser(id)
        }
    }
    fun getUserId(email:String){
        runBlocking {
            userId = dao.getUserId(email)
        }

    }

    fun updateUserName(name:String,userId:Long){
        runBlocking {
            dao.updateUserName(name,userId)
        }
    }
    fun updateUserSurname(surname:String,userId:Long){
        runBlocking {
            dao.updateUserEmail(surname,userId)
        }
    }
    fun updateUserEmail(email:String,userId:Long){
        runBlocking {
            dao.updateUserEmail(email,userId)
        }
    }

    fun getUser(){
        runBlocking {
            user = dao.getUserWithEmail(userEmail)
            _newUser.value = user
        }
    }


    fun addUser() {
        viewModelScope.launch {
            val user = User()
            user.userName = userName
            user.userSurname = userSurname
            user.userEmail = userEmail
            user.userPassword = userPassword
            dao.insert(user)
        }



    }



}