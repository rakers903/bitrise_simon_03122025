package com.example.cocktailmvi.cocktail.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.cocktailmvi.cocktail.data.local.cocktail.CocktailDao
import com.example.cocktailmvi.cocktail.data.local.cocktail.CocktailEntity

@Database(
    entities = [CocktailEntity::class],
    version = 1
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun cocktailDao(): CocktailDao
}