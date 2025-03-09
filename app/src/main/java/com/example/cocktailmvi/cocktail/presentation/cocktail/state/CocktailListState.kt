package com.example.cocktailmvi.cocktail.presentation.cocktail.state

import com.example.cocktailmvi.cocktail.domain.Cocktail

data class CocktailListState(
    val cocktails: List<CocktailCardState>
)