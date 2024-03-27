package com.amitapps.quotesapp.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface QuotesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(data: List<LocalResult>)

    @Query("SELECT * FROM quote_table")
    fun getAllData(): PagingSource<Int, LocalResult>
}