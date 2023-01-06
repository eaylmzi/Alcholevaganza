package com.example.alcholevaganza.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDao {
    @Insert
    suspend fun insert(user:User)
    @Update
    suspend fun update(user:User)
    @Delete
    suspend fun delete(user:User)

    @Query("SELECT * FROM user_table WHERE user_id = :userId")
    suspend fun get(userId : Long) : User

    @Query("SELECT * FROM user_table WHERE user_email = :userEmail and user_password = :userPassword")
    suspend fun validateUser(userEmail: String, userPassword: String) : User?

    @Query("SELECT * FROM user_table WHERE user_email = :userEmail")
    suspend fun getUserWithEmail(userEmail : String) : User

    @Query("SELECT user_id FROM user_table WHERE user_email = :userEmail")
    suspend fun getUserId(userEmail : String) : Long

    @Query("DELETE FROM user_table WHERE user_id = :userId")
    suspend fun deleteUser(userId : Long)

    @Query("UPDATE user_table SET user_name = :name WHERE user_id =:userId")
    suspend fun updateUserName(name : String,userId:Long)
    @Query("UPDATE user_table SET user_surname = :surname WHERE user_id =:userId")
    suspend fun updateUserSurname(surname:String,userId:Long)
    @Query("UPDATE user_table SET user_email = :email WHERE user_id =:userId")
    suspend fun updateUserEmail(email:String,userId:Long)
    @Query("UPDATE user_table SET user_password = :userPassword WHERE user_email =:userEmail")
    suspend fun updateUserPassword(userPassword : String,userEmail:String)

}