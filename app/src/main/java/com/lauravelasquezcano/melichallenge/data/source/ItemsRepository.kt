package com.lauravelasquezcano.melichallenge.data.source

import com.lauravelasquezcano.melichallenge.domain.Item
import com.lauravelasquezcano.melichallenge.domain.ResultWrapper
import com.lauravelasquezcano.melichallenge.domain.SearchResponse

interface ItemsRepository {

    suspend fun searchItems(query: String) : ResultWrapper<SearchResponse>

    suspend fun saveItem(item: Item) : Long
}