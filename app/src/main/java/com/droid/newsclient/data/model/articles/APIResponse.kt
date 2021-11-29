package com.droid.newsclient.data.model.articles


import com.google.gson.annotations.SerializedName

data class APIResponse(
        @SerializedName("articles")
        val articles: List<Article>,
        @SerializedName("status")
        val status: String,
        @SerializedName("totalResults")
        val totalResults: Int
)