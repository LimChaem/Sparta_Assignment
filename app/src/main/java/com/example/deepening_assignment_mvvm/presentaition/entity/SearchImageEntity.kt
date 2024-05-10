package com.example.deepening_assignment_mvvm.presentaition.entity

import android.util.Log
import com.example.deepening_assignment_mvvm.data.remote.Meta

data class SearchImageEntity(
    val meta: Meta,
    val items: List<DocumentsEntity>
)

data class DocumentsEntity(
    val thumbnailUrl: String,
    val displaySiteName: String,
    val dateTime: String,
    var isLike: Boolean = false
) {
    fun toggleLike() {
        isLike = !isLike
        Log.d("isLike", "isLike =$isLike")
    }
}
