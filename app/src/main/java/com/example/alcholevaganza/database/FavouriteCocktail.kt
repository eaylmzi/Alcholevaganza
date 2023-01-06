package com.example.alcholevaganza.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favouritecocktail_table")
data class FavouriteCocktail(
    @ColumnInfo(name = "favouritecocktail_id")
    @PrimaryKey(autoGenerate = true)
    var Id: Long = 0L,
    @ColumnInfo (name = "user_id")
    var userId : Long = 0L,
    @ColumnInfo(name = "cocktail_id")
    var cocktailId : Long = 0L
)