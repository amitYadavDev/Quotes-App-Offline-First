package com.amitapps.quotesapp.di

import android.content.Context
import androidx.room.Room
import com.amitapps.quotesapp.data.local.QuoteDatabase
import com.amitapps.quotesapp.data.local.QuotesDao
import com.amitapps.quotesapp.data.network.QuoteApiService
import com.amitapps.quotesapp.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RetrofitModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideQuoteAPI(retrofit: Retrofit): QuoteApiService {
        return retrofit.create(QuoteApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context) : QuoteDatabase {
        return Room.databaseBuilder(
            context,
            QuoteDatabase::class.java,
            "quote_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideQuoteDao(database: QuoteDatabase): QuotesDao {
        return database.quotesDao()
    }
}