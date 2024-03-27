package com.amitapps.quotesapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [LocalResult::class], version = 1, exportSchema = false)
abstract class QuoteDatabase: RoomDatabase() {
    abstract fun quotesDao(): QuotesDao
}