package com.example.alcholevaganza.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query

@Dao
interface CocktailDao {

    @Query("SELECT * FROM cocktail_table WHERE cocktail_ingredients LIKE :cocktailIngredients")
    suspend fun getCocktailsByName(cocktailIngredients : String ): List<Cocktail>

    @Query("SELECT * FROM cocktail_table WHERE cocktail_name = :cocktailName")
    suspend fun getSpecific(cocktailName : String): List<Cocktail>

    @Query("SELECT * FROM cocktail_table WHERE cocktail_id = :cocktailId")
    suspend fun getSpecificWithId(cocktailId : Long): Cocktail

}