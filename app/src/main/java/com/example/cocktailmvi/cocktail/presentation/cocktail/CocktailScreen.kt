package com.example.cocktailmvi.cocktail.presentation.cocktail

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cocktailmvi.cocktail.presentation.cocktail.components.CocktailList
import com.example.cocktailmvi.cocktail.presentation.cocktail.components.SearchBar

@Composable
fun CocktailScreen(
    modifier: Modifier = Modifier,
    cocktailViewModel: CocktailViewModel = hiltViewModel(),
) {
    val state = cocktailViewModel.state
    val onAction = cocktailViewModel::onAction
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Text(
            text = "Cocktail Screen",
            style = MaterialTheme.typography.headlineSmall
        )
        SearchBar(
            state = state.searchBarState,
            onAction = onAction,
            modifier = Modifier.fillMaxWidth()
        )
        CocktailList(
            state = state.cocktailListState,
            onAction = onAction
        )
    }
}