package com.example.cocktailmvi.cocktail.data.local.cocktail

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cocktail")
data class CocktailEntity(
    @PrimaryKey
    val id: String,
    @ColumnInfo(name = "is_liked")
    val isLiked: Boolean
)