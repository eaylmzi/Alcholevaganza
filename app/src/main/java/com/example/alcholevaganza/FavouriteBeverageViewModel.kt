package com.example.alcholevaganza

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


import com.example.alcholevaganza.database.FavouriteBeverage
import com.example.alcholevaganza.database.FavouriteBeverageDao

import kotlinx.coroutines.runBlocking

class FavouriteBeverageViewModel(val dao: FavouriteBeverageDao) : ViewModel() {

    var beverageName = ""


    var beverage: FavouriteBeverage? = FavouriteBeverage()

    private val _items = MutableLiveData<List<FavouriteBeverage>>()
    val favouriteBeverages: LiveData<List<FavouriteBeverage>>
        get() = _items


    init {
        _items.value = emptyList()
    }

    fun findBeverage(userId: Long, cocktailId: Long) {
        runBlocking {
            beverage = dao.getBeverage(userId, cocktailId)
        }
    }

    fun addFavourite(userId: Long, beverageId: Long) {
        runBlocking {
            val favouriteBeverage = FavouriteBeverage()
            favouriteBeverage.beverageId = beverageId
            favouriteBeverage.userId = userId
            dao.insert(favouriteBeverage)
        }
    }

    fun getAll(userId: Long) {
        runBlocking {
            _items.value = dao.getAll(userId)
        }
    }
    fun deleteBeverage(userId: Long,beverageId:Long) {
        runBlocking {
            dao.deleteBeverage(userId,beverageId)
        }
    }


}