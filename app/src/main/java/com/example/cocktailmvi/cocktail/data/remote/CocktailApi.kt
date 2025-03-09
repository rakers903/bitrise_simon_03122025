package com.example.cocktailmvi.cocktail.data.remote

import com.example.cocktailmvi.cocktail.domain.Cocktail
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CocktailApi {
    @GET("api/json/v1/1/search.php")
    suspend fun getCocktails(
        @Query("f") letter: String = "a"
    ): Response<CocktailResponse>
}