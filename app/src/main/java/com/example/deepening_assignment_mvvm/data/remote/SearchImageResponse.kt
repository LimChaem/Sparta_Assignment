package com.example.deepening_assignment_mvvm.data.remote

import com.google.gson.annotations.SerializedName

data class SearchImageResponse(
    @SerializedName("meta")
val meta: Meta,
    @SerializedName("documents")
val documents: List<Documents>
)

data class Meta(
    @SerializedName("total_count")
    val total_count: Int,
    @SerializedName("pageable_count")
    val pageable_count: Int,
    @SerializedName("is_end")
    val is_end: Boolean
)

data class Documents(
    @SerializedName("thumbnail_url")
    val thumbnailUrl: String,
    @SerializedName("display_sitename")
    val displaySiteName: String,
    @SerializedName("datetime")
    val dateTime: String
)
