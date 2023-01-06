package com.example.alcholevaganza.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "beverage_table")
data class Beverage(
    @ColumnInfo(name = "beverage_id")
    @PrimaryKey(autoGenerate = true)
    var Id: Long = 0L,
    @ColumnInfo(name = "beverage_type")
    var beverageType: String = "",
    @ColumnInfo(name = "beverage_name")
    var beverageName: String = ""

    )