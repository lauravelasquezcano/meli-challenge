package com.lauravelasquezcano.melichallenge.data.repository

import com.lauravelasquezcano.melichallenge.data.remote.SearchApi
import com.lauravelasquezcano.melichallenge.data.source.ItemsRepository
import com.lauravelasquezcano.melichallenge.domain.ResultWrapper
import com.lauravelasquezcano.melichallenge.domain.SearchResponse
import javax.inject.Inject

class ItemsRepositoryImpl @Inject constructor(
    private val service: SearchApi
) : ItemsRepository {

    override suspend fun searchItems(query: String): ResultWrapper<SearchResponse> {
        val response = service.getAllItems(query)
        return if (response.isSuccessful)
            ResultWrapper.Success(response.body()!!)
        else
            ResultWrapper.GenericError(response.code(), response.message())
    }
}