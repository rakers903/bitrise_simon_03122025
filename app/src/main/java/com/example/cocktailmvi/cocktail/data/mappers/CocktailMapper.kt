package com.example.cocktailmvi.cocktail.data.mappers

import com.example.cocktailmvi.cocktail.data.remote.CocktailDto
import com.example.cocktailmvi.cocktail.domain.Cocktail

fun CocktailDto.toCocktail(): Cocktail {
    return Cocktail(
        id = idDrink,
        name = strDrink,
        instructions = strInstructions,
        isLiked = false
    )
}

fun List<CocktailDto>.toCocktailList(): List<Cocktail> {
    return this.map {
        it.toCocktail()
    }
}