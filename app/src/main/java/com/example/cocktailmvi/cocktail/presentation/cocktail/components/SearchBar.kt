package com.example.cocktailmvi.cocktail.presentation.cocktail.components

import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.cocktailmvi.cocktail.presentation.cocktail.CocktailAction
import com.example.cocktailmvi.cocktail.presentation.cocktail.state.SearchBarState

@Composable
fun SearchBar(
    state: SearchBarState,
    onAction: (CocktailAction) -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        modifier = modifier,
        value = state.searchQuery,
        onValueChange = { onAction(CocktailAction.UpdateSearch(it.take(1))) },
        label = {
            Text(text = "Letter")
        }
    )
}