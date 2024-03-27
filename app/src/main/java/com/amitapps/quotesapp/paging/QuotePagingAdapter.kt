package com.amitapps.quotesapp.paging

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.amitapps.quotesapp.R
import com.amitapps.quotesapp.data.local.LocalResult
import com.amitapps.quotesapp.data.model.Result

class QuotePagingAdapter : PagingDataAdapter<LocalResult, QuotePagingAdapter.QuoteViewHolder>(COMPARATOR) {

    class QuoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val quote = itemView.findViewById<TextView>(R.id.quote)
    }

    override fun onBindViewHolder(holder: QuotePagingAdapter.QuoteViewHolder, position: Int) {
        val item = getItem(position)
        item?.let {
            holder.quote.text = it.content
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): QuotePagingAdapter.QuoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.quote_item, parent, false)
        return QuoteViewHolder(view)
    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<LocalResult>() {
            override fun areItemsTheSame(oldItem: LocalResult, newItem: LocalResult): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: LocalResult, newItem: LocalResult): Boolean {
                return oldItem == newItem
            }

        }
    }
}