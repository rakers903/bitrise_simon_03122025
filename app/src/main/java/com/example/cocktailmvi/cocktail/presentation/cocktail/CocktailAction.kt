package com.example.cocktailmvi.cocktail.presentation.cocktail

sealed class CocktailAction {
    data object GoBack: CocktailAction()
    data class ToggleLike(val id: String, val liked: Boolean): CocktailAction()
    data class Search(val searchQuery: String): CocktailAction()
    data class UpdateSearch(val text: String): CocktailAction()
}