package com.example.alcholevaganza

import com.example.alcholevaganza.database.*

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class FavouriteCocktailViewModel(val dao: FavouriteCocktailDao) : ViewModel() {

    var cocktailId = 0L


    var cocktail : FavouriteCocktail? = FavouriteCocktail()

    private val _items = MutableLiveData<List<FavouriteCocktail?>>()
    val favouriteCocktail: LiveData<List<FavouriteCocktail?>>
        get() = _items

    init {
        _items.value = emptyList()
    }

    fun addFavourite(userId : Long, cocktailId : Long) {
        runBlocking {
            val cocktail = FavouriteCocktail()
            cocktail.userId = userId
            cocktail.cocktailId = cocktailId

            dao.insert(cocktail)

        }
    }
    fun findCocktail(userId:Long ,cocktailId : Long) {
        runBlocking {
            cocktail = dao.getCocktailWithCocktailId(userId,cocktailId)

        }
    }

    fun getAll(userId: Long) {
        runBlocking {
            _items.value = dao.getAll(userId)
        }
    }
    fun deleteCocktail(userId: Long,cocktailId:Long) {
        runBlocking {
            dao.deleteCocktail(userId,cocktailId)
        }
    }


}