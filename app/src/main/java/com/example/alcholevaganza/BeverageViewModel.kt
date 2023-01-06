package com.example.alcholevaganza

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.alcholevaganza.database.Beverage
import com.example.alcholevaganza.database.BeverageDao
import androidx.lifecycle.ViewModel

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class BeverageViewModel(val dao: BeverageDao) : ViewModel() {
    var beverageType = ""
    var beverageName = ""

    var beverageList = dao.getAll()

    //var beverages :LiveData<List<Beverage>> = LiveData<List<Beverage>>()
    var beverage : Beverage? = Beverage()

    private val _items = MutableLiveData<List<Beverage>>()
    val beverages: LiveData<List<Beverage>>
        get() = _items



    private val _navigateToTaskBeverage = MutableLiveData<Beverage?>()
    val navigateToTaskBeverage:LiveData<Beverage?>
        get()= _navigateToTaskBeverage


    init {
        _items.value = emptyList()
    }

    fun getSpesific() {

        viewModelScope.launch {
            _items.value = dao.getSpecific(beverageType)
        }

    }
    fun addItemToBeveragesList(beverageId : Long){
        viewModelScope.launch {
            _items.value =_items.value?.plus(dao.getSpecificWithId(beverageId))
        }
    }
    fun getSpesificWithParameter(beverageId :Long) {

        runBlocking {
            beverage = dao.getSpecificWithId(beverageId)
        }

    }



    fun onTaskClickedBeverage(beverageType : Beverage){
        _navigateToTaskBeverage.value = beverageType
    }
    fun onTaskNavigatedBeverage(){
        _navigateToTaskBeverage.value=null
    }


}