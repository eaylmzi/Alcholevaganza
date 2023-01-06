package com.example.alcholevaganza.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface FavouriteCocktailDao {
    @Insert
    suspend fun insert(favouriteCocktail: FavouriteCocktail)
    @Update
    suspend fun update(favouriteCocktail: FavouriteCocktail)
    @Delete
    suspend fun delete(favouriteCocktail: FavouriteCocktail)

    @Query("SELECT * FROM favouritecocktail_table WHERE user_id = :userId and cocktail_id = :cocktailId")
    suspend fun getCocktailWithCocktailId(userId : Long,cocktailId : Long) : FavouriteCocktail?
    @Query("SELECT * FROM favouritecocktail_table WHERE user_id = :userId")
    suspend fun getAll(userId : Long) : List<FavouriteCocktail>
    @Query("DELETE FROM favouritecocktail_table WHERE user_id = :userId and cocktail_id = :cocktailId")
    suspend fun deleteCocktail(userId : Long,cocktailId : Long)

}