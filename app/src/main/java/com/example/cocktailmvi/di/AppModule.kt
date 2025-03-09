package com.example.cocktailmvi.di

import com.example.cocktailmvi.cocktail.data.remote.CocktailApi
import com.example.cocktailmvi.cocktail.data.repository.CocktailRepositoryImpl
import com.example.cocktailmvi.cocktail.domain.Cocktail
import com.example.cocktailmvi.cocktail.domain.repository.CocktailRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun providesCocktailApi(): CocktailApi {
        return Retrofit.Builder()
            .baseUrl("https://www.thecocktaildb.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun providesCocktailRepository(
        cocktailApi: CocktailApi
    ): CocktailRepository {
        return CocktailRepositoryImpl(
            cocktailApi = cocktailApi
        )
    }
}