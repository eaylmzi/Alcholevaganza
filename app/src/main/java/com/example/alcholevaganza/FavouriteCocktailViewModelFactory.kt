package com.example.alcholevaganza


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.alcholevaganza.database.FavouriteCocktailDao
import java.lang.IllegalArgumentException


class FavouriteCocktailViewModelFactory(private val dao: FavouriteCocktailDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FavouriteCocktailViewModel::class.java)) {
            return FavouriteCocktailViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel")

    }

}