package com.example.cocktailmvi.cocktail.data.repository

import android.util.Log
import com.example.cocktailmvi.cocktail.data.mappers.toCocktailList
import com.example.cocktailmvi.cocktail.data.remote.CocktailApi
import com.example.cocktailmvi.cocktail.domain.Cocktail
import com.example.cocktailmvi.cocktail.domain.repository.CocktailRepository
import com.example.cocktailmvi.util.UIState
import javax.inject.Inject

class CocktailRepositoryImpl @Inject constructor(
    val cocktailApi: CocktailApi
): CocktailRepository {
    override suspend fun findByFirstLetter(searchQuery: String): UIState<List<Cocktail>> {
        try {
            val cocktailResponse = cocktailApi.getCocktails(searchQuery)
            if(cocktailResponse.isSuccessful) {
                val body = cocktailResponse.body()
                if(body != null) {
                    if(body.cocktails != null) {
                        return UIState.SUCCESS(body.cocktails.toCocktailList())
                    }
                    Log.d("CocktailRepoImpl", "Cocktails is null")
                } else {
                    return UIState.ERROR("cocktailResponse was successful but the body is null")
                }
            }
            return UIState.ERROR("An unknown error has occurred in cocktailAPI: findByFirstLetter")
        } catch(e: Exception) {
            e.printStackTrace()
            Log.d("CocktailRepositoryImpl", e.message.toString())
            return UIState.ERROR("Could not find cocktail: findByFirstLetter")
        }
    }
}