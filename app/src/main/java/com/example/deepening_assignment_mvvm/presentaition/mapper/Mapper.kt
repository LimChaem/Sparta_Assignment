package com.example.deepening_assignment_mvvm.presentaition.mapper

import com.example.deepening_assignment_mvvm.data.remote.Documents
import com.example.deepening_assignment_mvvm.data.remote.SearchImageResponse
import com.example.deepening_assignment_mvvm.presentaition.entity.DocumentsEntity
import com.example.deepening_assignment_mvvm.presentaition.entity.SearchImageEntity

fun List<Documents>.asEntity(): List<DocumentsEntity> {
    return map {
        DocumentsEntity(
            it.thumbnailUrl,
            it.displaySiteName,
            it.dateTime
        )
    }
}

fun SearchImageResponse.toEntity() = SearchImageEntity(
    meta = meta,
    items = documents.asEntity()
)