package com.example.cocktailmvi.cocktail.presentation.cocktail.state

data class CocktailCardState(
    val id: String,
    val name: String,
    val instructions: String,
    val isLiked: Boolean
)