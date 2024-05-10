package com.example.deepening_assignment_mvvm.presentaition.entity

import com.example.deepening_assignment_mvvm.data.remote.SearchRemoteDataSource
import com.example.deepening_assignment_mvvm.data.repository.SearchImageRepository
import com.example.deepening_assignment_mvvm.presentaition.mapper.toEntity

class SearchImageImpl(
    private val dataSource: SearchRemoteDataSource
) : SearchImageRepository {
    override suspend fun getImage(searchKey: String): SearchImageEntity {
        return dataSource.searchImage(searchKey).toEntity()
    }

}
