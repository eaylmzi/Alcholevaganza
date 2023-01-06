package com.example.alcholevaganza


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.alcholevaganza.database.FavouriteBeverageDao
import java.lang.IllegalArgumentException


class FavouriteBeverageViewModelFactory(private val dao: FavouriteBeverageDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FavouriteBeverageViewModel::class.java)) {
            return FavouriteBeverageViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel")

    }

}