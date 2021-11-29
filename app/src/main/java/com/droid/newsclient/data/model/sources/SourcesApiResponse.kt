package com.droid.newsclient.data.model.sources


import com.google.gson.annotations.SerializedName

data class SourcesApiResponse(
        @SerializedName("sources")
        val sources: List<Source>,
        @SerializedName("status")
        val status: String
)