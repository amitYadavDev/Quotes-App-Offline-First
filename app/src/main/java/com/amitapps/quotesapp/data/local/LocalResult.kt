package com.amitapps.quotesapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "quote_table")
data class LocalResult(
    @PrimaryKey(autoGenerate = true)
    val i: Int = 0,
    val id: String,
    val author: String,
    val authorSlug: String,
    val content: String,
    val dateAdded: String,
    val dateModified: String,
    val length: Int
//    val tags: List<String>
)