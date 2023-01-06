package com.example.alcholevaganza.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favouritebeverage_table")
data class FavouriteBeverage(

    @ColumnInfo(name = "favouritebeverage_id")
    @PrimaryKey(autoGenerate = true)
    var Id: Long = 0L,
    @ColumnInfo (name = "user_id")
    var userId : Long = 0L,
    @ColumnInfo(name = "beverage_id")
    var beverageId : Long = 0L
)