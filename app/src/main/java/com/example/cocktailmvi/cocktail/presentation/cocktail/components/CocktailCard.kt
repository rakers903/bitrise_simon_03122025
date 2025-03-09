package com.example.cocktailmvi.cocktail.presentation.cocktail.components

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.cocktailmvi.R
import com.example.cocktailmvi.cocktail.domain.Cocktail
import com.example.cocktailmvi.cocktail.presentation.cocktail.CocktailAction
import com.example.cocktailmvi.cocktail.presentation.cocktail.state.CocktailCardState

@Composable
fun CocktailCard(
    state: CocktailCardState,
    onAction: (CocktailAction) -> Unit,
    modifier: Modifier = Modifier
) {
    Log.d("State", state.isLiked.toString())
    Surface(
        shadowElevation = 3.dp,
        shape = RoundedCornerShape(6.dp)
    ) {
        Column(
            modifier = modifier
//                .clickable {
//                    onAction(CocktailAction.ToggleLike(state.id))
//                }
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        )  {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = state.name,
                    style = MaterialTheme.typography.titleLarge
                )
                Icon(
                    painter = painterResource(R.drawable.sharp_bookmark),
                    contentDescription = "Bookmark Icon",
                    modifier = Modifier
                        .size(28.dp)
                        .clickable {
                            onAction(CocktailAction.ToggleLike(state.id))
                        },
                    tint = if(state.isLiked) colorResource(R.color.yellow) else Color.Gray,
                )
            }
            Text(
                text = state.instructions
            )

        }
    }
}