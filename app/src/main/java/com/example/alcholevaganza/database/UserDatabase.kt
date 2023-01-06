package com.example.alcholevaganza.database
import android.content.Context
import androidx.room.RoomDatabase
import androidx.room.Database
import androidx.room.Room

@Database(entities = [User::class,Beverage::class,Cocktail::class,FavouriteBeverage::class,FavouriteCocktail::class], version = 5, exportSchema = false)
abstract class UserDatabase : RoomDatabase() {
    abstract val userDao : UserDao
    abstract val beverageDao : BeverageDao
    abstract val cocktailDao : CocktailDao
    abstract val favouriteBeverage : FavouriteBeverageDao
    abstract val favouriteCocktail : FavouriteCocktailDao
    companion object{
        @Volatile
        private var INSTANCE : UserDatabase? = null
        fun getInstance(context : Context) : UserDatabase{
            synchronized(this){
                var instance = INSTANCE
                if (instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        UserDatabase::class.java,
                        "user_database"
                    ).fallbackToDestructiveMigration()
                    .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}