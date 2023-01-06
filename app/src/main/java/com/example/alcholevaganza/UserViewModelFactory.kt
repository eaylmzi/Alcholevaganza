package com.example.alcholevaganza

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.alcholevaganza.database.UserDao
import java.lang.IllegalArgumentException


class UserViewModelFactory(private val dao: UserDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            return UserViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel")

    }

}