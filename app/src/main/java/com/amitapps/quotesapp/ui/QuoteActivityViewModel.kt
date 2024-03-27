package com.amitapps.quotesapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.amitapps.quotesapp.repository.QuoteDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class QuoteActivityViewModel @Inject constructor(private val repository: QuoteDataRepository) : ViewModel() {

    val quoteData = repository.getQuotes().cachedIn(viewModelScope)
}