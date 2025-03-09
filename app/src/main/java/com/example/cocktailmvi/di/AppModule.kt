package com.example.cocktailmvi.di

import android.app.Application
import androidx.room.Room
import com.example.cocktailmvi.cocktail.data.local.AppDatabase
import com.example.cocktailmvi.cocktail.data.local.cocktail.CocktailDao
import com.example.cocktailmvi.cocktail.data.remote.CocktailApi
import com.example.cocktailmvi.cocktail.data.repository.CocktailRepositoryImpl
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
    fun providesCocktailDatabase(
        application: Application
    ): AppDatabase {
        return Room.databaseBuilder(
            application,
            AppDatabase::class.java,
            "cocktails"
        )
        .build()
    }

    @Provides
    @Singleton
    fun providesCocktailDao(
        appDatabase: AppDatabase
    ): CocktailDao = appDatabase.cocktailDao()

    @Provides
    @Singleton
    fun providesCocktailRepository(
        cocktailApi: CocktailApi,
        cocktailDao: CocktailDao
    ): CocktailRepository {
        return CocktailRepositoryImpl(
            cocktailApi = cocktailApi,
            cocktailDao = cocktailDao
        )
    }

}