package com.example.cocktailmvi.cocktail.domain

data class Cocktail(
    val id: String,
    val name: String,
    val instructions: String,
    val isLiked: Boolean
)