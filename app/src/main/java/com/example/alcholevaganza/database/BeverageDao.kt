package com.example.alcholevaganza.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface BeverageDao {

    @Query("SELECT * FROM beverage_table WHERE beverage_id = :beverageId")
    fun get(beverageId: Long): LiveData<Beverage>

    @Query("SELECT * FROM beverage_table ORDER BY beverage_id DESC")
    fun getAll(): LiveData<List<Beverage>>
    @Query("SELECT * FROM beverage_table WHERE beverage_type = :beverageType")
    suspend fun getSpecific(beverageType : String): List<Beverage>
    @Query("SELECT * FROM beverage_table WHERE beverage_id = :beverageId")
    suspend fun getSpecificWithId(beverageId : Long): Beverage




}