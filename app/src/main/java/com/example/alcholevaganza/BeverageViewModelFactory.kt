package com.example.alcholevaganza

import com.example.alcholevaganza.database.BeverageDao
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException


class BeverageViewModelFactory(private val dao: BeverageDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BeverageViewModel::class.java)) {
            return BeverageViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel")

    }

}