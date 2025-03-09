package com.example.cocktailmvi.cocktail.data.repository

import android.util.Log
import com.example.cocktailmvi.cocktail.data.local.AppDatabase
import com.example.cocktailmvi.cocktail.data.local.cocktail.CocktailDao
import com.example.cocktailmvi.cocktail.data.local.cocktail.CocktailEntity
import com.example.cocktailmvi.cocktail.data.mappers.toCocktailList
import com.example.cocktailmvi.cocktail.data.remote.CocktailApi
import com.example.cocktailmvi.cocktail.domain.Cocktail
import com.example.cocktailmvi.cocktail.domain.repository.CocktailRepository
import com.example.cocktailmvi.util.UIState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.Dispatcher
import javax.inject.Inject

class CocktailRepositoryImpl @Inject constructor(
    val cocktailApi: CocktailApi,
    val cocktailDao: CocktailDao
): CocktailRepository {
    override suspend fun findByFirstLetter(searchQuery: String): UIState<List<Cocktail>> {
        try {
            val cocktailLikes = cocktailDao.getAll()
            val cocktailResponse = cocktailApi.getCocktails(searchQuery)
            if (cocktailResponse.isSuccessful) {
                val body = cocktailResponse.body()
                if (body != null) {
                    if (body.cocktails != null) {
                        val cocktailList = body.cocktails.toCocktailList()
                        val cocktailListWithLikes = cocktailList.map { cocktail ->
                            val isLiked = cocktailLikes.any { it.id  == cocktail.id && it.isLiked }
                            cocktail.copy(
                                isLiked = isLiked
                            )
                        }
                        return UIState.SUCCESS(cocktailListWithLikes.sortedByDescending {
                            it.isLiked
                        })
                    }
                    return UIState.ERROR("cocktailResponse was successful but cocktails is null")
                } else {
                    return UIState.ERROR("cocktailResponse was successful but the body is null")
                }
            } else {
                return UIState.ERROR("cocktailResponse was successful but the body is null")
            }
        } catch (e: Exception) {
            return UIState.ERROR(error = "Could not get CocktailLikes from the database")
        }
    }

    override suspend fun saveLike(id: String, liked: Boolean) {
        try {
            cocktailDao.save(CocktailEntity(id, liked))
        } catch(e: Exception) {
            Log.d("CocktailRepo","Could not save Cocktail")
        }
    }
}