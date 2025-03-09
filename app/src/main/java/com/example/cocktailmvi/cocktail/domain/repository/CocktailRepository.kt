package com.example.cocktailmvi.cocktail.domain.repository

import com.example.cocktailmvi.cocktail.domain.Cocktail
import com.example.cocktailmvi.util.UIState

interface CocktailRepository {
    suspend fun findByFirstLetter(searchQuery: String): UIState<List<Cocktail>>
}