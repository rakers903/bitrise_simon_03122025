package com.example.cocktailmvi.util

sealed class UIState<out T> {
    data object LOADING: UIState<Nothing>()
    data class ERROR<T>(val error: String): UIState<T>()
    data class SUCCESS<T>(val data: T): UIState<T>()
}