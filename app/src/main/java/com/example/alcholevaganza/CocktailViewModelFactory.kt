package com.example.alcholevaganza
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.alcholevaganza.database.CocktailDao
import java.lang.IllegalArgumentException


class CocktailViewModelFactory(private val dao: CocktailDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CocktailViewModel::class.java)) {
            return CocktailViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel")

    }

}