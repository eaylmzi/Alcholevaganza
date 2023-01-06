package com.example.alcholevaganza

import com.example.alcholevaganza.database.Cocktail
import com.example.alcholevaganza.database.CocktailDao
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class CocktailViewModel(val dao: CocktailDao) : ViewModel() {
    var cocktailName = ""


    var cocktail: Cocktail? = Cocktail()

    private val _items = MutableLiveData<List<Cocktail>>()
    val cocktails: LiveData<List<Cocktail>>
        get() = _items


    private val _navigateToTaskCocktail = MutableLiveData<Cocktail?>()
    val navigateToTaskCocktail: LiveData<Cocktail?>
        get() = _navigateToTaskCocktail


    init {
        _items.value = emptyList()
    }

    fun getSpesific(cocktailName: String) {

        runBlocking {
            _items.value = dao.getSpecific(cocktailName)
        }

    }

    fun getSpesificWithParameter(cocktailId: Long) {

        runBlocking {
            cocktail = dao.getSpecificWithId(cocktailId)
        }

    }

    fun addItemToCocktailList(cocktailId: Long) {
        viewModelScope.launch {
            _items.value = _items.value?.plus(dao.getSpecificWithId(cocktailId))
        }
    }

    fun getCocktailsByName(cocktailIngredientsName: String) {

        runBlocking {
            _items.value = dao.getCocktailsByName(cocktailIngredientsName)
        }

    }


    fun onTaskClickedCocktail(cocktail: Cocktail) {
        _navigateToTaskCocktail.value = cocktail
    }

    fun onTaskNavigatedCocktail() {
        _navigateToTaskCocktail.value = null
    }


}