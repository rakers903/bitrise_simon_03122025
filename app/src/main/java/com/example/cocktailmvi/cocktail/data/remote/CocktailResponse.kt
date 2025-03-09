package com.example.cocktailmvi.cocktail.data.remote

import com.google.gson.annotations.SerializedName
import retrofit2.http.Field

data class CocktailResponse(
    @SerializedName("drinks")
    val cocktails: List<CocktailDto>? = null
)