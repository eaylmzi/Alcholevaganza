package com.example.alcholevaganza.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cocktail_table")
data class Cocktail(

    @ColumnInfo (name = "cocktail_id")
    @PrimaryKey (autoGenerate = true)
    var Id : Long = 0L,
    @ColumnInfo(name = "cocktail_name")
    var cocktailName : String = "",
    @ColumnInfo(name = "cocktail_ingredients")
    var cocktailIngredients : String = "",

)