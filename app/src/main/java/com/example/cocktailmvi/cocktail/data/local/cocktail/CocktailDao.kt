package com.example.cocktailmvi.cocktail.data.local.cocktail

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CocktailDao {
    @Query("SELECT * from cocktail")
    fun getAll(): List<CocktailEntity>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(cocktail: CocktailEntity)
}