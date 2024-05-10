package com.example.deepening_assignment_mvvm.data.repository

import com.example.deepening_assignment_mvvm.data.remote.SearchRemoteDataSource
import com.example.deepening_assignment_mvvm.presentaition.entity.SearchImageEntity
import com.example.deepening_assignment_mvvm.presentaition.mapper.toEntity

class SearchImageRepositoryImpl(private val remoteDataSource: SearchRemoteDataSource): SearchImageRepository {
    override suspend fun getImage(searchKey: String): SearchImageEntity {
        return remoteDataSource.searchImage(searchKey).toEntity()

    }
}