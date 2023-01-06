package com.example.alcholevaganza.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface FavouriteBeverageDao {
    @Insert
    suspend fun insert(favouriteBeverage: FavouriteBeverage)
    @Update
    suspend fun update(favouriteBeverage: FavouriteBeverage)
    @Delete
    suspend fun delete(favouriteBeverage: FavouriteBeverage)

    @Query("SELECT * FROM favouritebeverage_table WHERE user_id = :userId and beverage_id = :beverageId")
    suspend fun getBeverage(userId : Long,beverageId : Long) : FavouriteBeverage?

    @Query("SELECT * FROM favouritebeverage_table WHERE user_id = :userId")
    suspend fun getAll(userId : Long) : List<FavouriteBeverage>

    @Query("DELETE FROM favouritebeverage_table WHERE user_id = :userId and beverage_id = :beverageId")
    suspend fun deleteBeverage(userId : Long,beverageId : Long)


}