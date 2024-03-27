package com.amitapps.quotesapp.data.model

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("_id")
    val id: String,
    val author: String,
    val authorSlug: String,
    val content: String,
    val dateAdded: String,
    val dateModified: String,
    val length: Int
//    val tags: List<String>
)