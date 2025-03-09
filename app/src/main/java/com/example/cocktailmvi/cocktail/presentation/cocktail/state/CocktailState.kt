package com.example.cocktailmvi.cocktail.presentation.cocktail.state

import com.example.cocktailmvi.cocktail.domain.Cocktail

data class CocktailState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val cocktailListState: CocktailListState = CocktailListState(
        cocktails = emptyList()
    ),
    val searchBarState: SearchBarState = SearchBarState(
        searchQuery = "a"
    )
)