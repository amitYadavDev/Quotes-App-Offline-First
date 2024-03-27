package com.amitapps.quotesapp.repository

import android.provider.ContactsContract
import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.amitapps.quotesapp.data.local.LocalResult
import com.amitapps.quotesapp.data.local.QuoteDatabase
import com.amitapps.quotesapp.data.local.QuotesDao
import com.amitapps.quotesapp.data.network.QuoteApiService

@OptIn(ExperimentalPagingApi::class)
class DataRemoteMediator(
    private val apiService: QuoteApiService,
    private val dataDao: QuotesDao
) : RemoteMediator<Int, LocalResult>() {
    @OptIn(ExperimentalPagingApi::class)
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, LocalResult>
    ): MediatorResult {
        try {
            val page: Int = when (loadType) {
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> 10
            }

            val response = apiService.getQuotes(page)
            Log.d("DataRemoteMediator", response.results.toString())
            if (response != null) {
                val data: List<LocalResult> = listOf(response.results as LocalResult)
                Log.d("DataRemoteMediator insert", data.toString())
                dataDao.insertAll(data)
                return MediatorResult.Success(endOfPaginationReached = data.isEmpty())
            } else {
                return MediatorResult.Error(Exception("response.results"))
            }
        } catch (e: Exception) {
            return MediatorResult.Error(e)
        }
    }
}
