package com.example.alcholevaganza.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class User(

    @ColumnInfo (name = "user_id")
    @PrimaryKey (autoGenerate = true)
    var Id : Long = 0L,
    @ColumnInfo(name = "user_name")
    var userName : String = "",
    @ColumnInfo(name = "user_surname")
    var userSurname : String = "",
    @ColumnInfo(name = "user_password")
    var userPassword :String = "",
    @ColumnInfo(name = "user_email")
    var userEmail : String = ""
)
