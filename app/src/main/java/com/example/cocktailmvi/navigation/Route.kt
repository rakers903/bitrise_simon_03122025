package com.example.cocktailmvi.navigation

import kotlinx.serialization.Serializable

sealed class Route {
    @Serializable
    data object CocktailRoute : Route()
    @Serializable
    data object CocktailDetailRoute : Route()
}