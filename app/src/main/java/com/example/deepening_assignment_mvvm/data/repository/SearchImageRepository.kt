package com.example.deepening_assignment_mvvm.data.repository

import com.example.deepening_assignment_mvvm.presentaition.entity.SearchImageEntity

interface SearchImageRepository {
    suspend fun getImage(searchKey: String): SearchImageEntity
}