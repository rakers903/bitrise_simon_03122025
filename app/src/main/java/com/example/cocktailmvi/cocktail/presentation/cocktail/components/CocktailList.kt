package com.example.cocktailmvi.cocktail.presentation.cocktail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.cocktailmvi.cocktail.presentation.cocktail.CocktailAction
import com.example.cocktailmvi.cocktail.presentation.cocktail.state.CocktailListState

@Composable
fun CocktailList(
    state: CocktailListState,
    onAction: (CocktailAction) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(state.cocktails) { cocktail ->
            CocktailCard(
                state = cocktail,
                onAction = onAction
            )
        }
    }
}