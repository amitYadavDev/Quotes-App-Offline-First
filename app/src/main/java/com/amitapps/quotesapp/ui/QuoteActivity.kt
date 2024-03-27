package com.amitapps.quotesapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.amitapps.quotesapp.R
import com.amitapps.quotesapp.paging.QuotePagingAdapter
import dagger.hilt.android.AndroidEntryPoint
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager


@AndroidEntryPoint
class QuoteActivity : AppCompatActivity() {
    lateinit var quoteViewModel: QuoteActivityViewModel
    lateinit var recyclerView: RecyclerView
    lateinit var quoteAdapter: QuotePagingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById<RecyclerView>(R.id.quoteList)
        quoteViewModel = ViewModelProvider(this).get(QuoteActivityViewModel::class.java)

        quoteAdapter = QuotePagingAdapter()

        recyclerView.layoutManager = GridLayoutManager(this, 2)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = quoteAdapter

        Log.d("quoteViewModel", "  ok")


        quoteViewModel.quoteData.observe(this, Observer {
            quoteAdapter.submitData(lifecycle, it)
        })
    }
}