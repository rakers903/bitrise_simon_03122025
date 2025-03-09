package com.example.cocktailmvi.cocktail.presentation.cocktail

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cocktailmvi.cocktail.domain.repository.CocktailRepository
import com.example.cocktailmvi.cocktail.presentation.cocktail.state.CocktailCardState
import com.example.cocktailmvi.cocktail.presentation.cocktail.state.CocktailListState
import com.example.cocktailmvi.cocktail.presentation.cocktail.state.CocktailState
import com.example.cocktailmvi.cocktail.presentation.cocktail.state.SearchBarState
import com.example.cocktailmvi.util.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.http.Query
import javax.inject.Inject

@HiltViewModel
class CocktailViewModel @Inject constructor(
    private val cocktailRepository: CocktailRepository
): ViewModel() {
    var state by mutableStateOf(CocktailState())

    fun onAction(action: CocktailAction) {
        when(action) {
            is CocktailAction.GoBack -> {}
            is CocktailAction.ToggleLike -> toggleLike(action.id, action.liked)
            is CocktailAction.Search -> search(action.searchQuery)
            is CocktailAction.UpdateSearch -> updateSearch(action.text)
        }
    }

    init {
        search("a")
    }

    private fun toggleLike(id: String, liked: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            cocktailRepository.saveLike(id, liked)
            state = state.copy(
                cocktailListState = CocktailListState(
                    cocktails = state.cocktailListState.cocktails.map {
                        if(id == it.id) {
                            return@map it.copy(
                                id = it.id,
                                name = it.name,
                                isLiked = !it.isLiked
                            )
                        }
                        it
                    }
                )
            )
        }
    }

    private fun search(searchQuery: String) {
        state = state.copy(isLoading = true)
        viewModelScope.launch(Dispatchers.IO) {
            when(val cocktailData = cocktailRepository.findByFirstLetter(searchQuery)) {
                is UIState.SUCCESS -> {
                    Log.d("Search", "SUCCESS")
                    state = state.copy(
                        isLoading = false,
                        cocktailListState = CocktailListState(
                            cocktails = cocktailData.data.map {
                                CocktailCardState(
                                    id = it.id,
                                    name = it.name,
                                    instructions = it.instructions,
                                    isLiked = it.isLiked
                                )
                            }
                        ),
                        error = null
                    )
                }
                is UIState.LOADING -> {
                    Log.d("Search", "LOADING")
                    state = state.copy(
                        isLoading = true,
                        cocktailListState = CocktailListState(
                            cocktails = emptyList()
                        ),
                        error = null
                    )
                }
                is UIState.ERROR -> {
                    Log.d("Search", cocktailData.error)
                    state = state.copy(
                        isLoading = false,
                        cocktailListState = CocktailListState(
                            cocktails = emptyList()
                        ),
                        error = cocktailData.error
                    )
                }
            }
        }
    }

    private fun updateSearch(searchQuery: String) {
        state = state.copy(
            searchBarState = SearchBarState(
                searchQuery = searchQuery
            )
        )
        search(searchQuery)
    }
}