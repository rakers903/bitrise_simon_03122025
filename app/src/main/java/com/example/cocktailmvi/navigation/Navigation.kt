package com.example.cocktailmvi.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cocktailmvi.cocktail.presentation.cocktail.CocktailScreen
import com.example.cocktailmvi.cocktail.presentation.cocktail_detail.CocktailDetailScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    Column(
        modifier = Modifier
            .padding(16.dp)
    ) {
        NavHost(
            navController = navController,
            startDestination = Route.CocktailRoute
        ) {
            composable<Route.CocktailRoute> {
                CocktailScreen()
            }
            composable<Route.CocktailDetailRoute> {
                CocktailDetailScreen()
            }
        }
    }
}