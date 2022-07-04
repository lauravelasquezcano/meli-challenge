package com.lauravelasquezcano.melichallenge.data.repository

import com.lauravelasquezcano.melichallenge.app.database.DbItem
import com.lauravelasquezcano.melichallenge.data.remote.SearchApi
import com.lauravelasquezcano.melichallenge.data.source.ItemsRepository
import com.lauravelasquezcano.melichallenge.data.source.LocalDataSource
import com.lauravelasquezcano.melichallenge.domain.Item
import com.lauravelasquezcano.melichallenge.domain.ResultWrapper
import com.lauravelasquezcano.melichallenge.domain.SearchResponse
import javax.inject.Inject

class ItemsRepositoryImpl @Inject constructor(
    private val service: SearchApi,
    private val localDataSource: LocalDataSource
) : ItemsRepository {

    override suspend fun searchItems(query: String): ResultWrapper<SearchResponse> {
        val response = service.getAllItems(query)
        return if (response.isSuccessful)
            ResultWrapper.Success(response.body()!!)
        else
            ResultWrapper.GenericError(response.code(), response.message())
    }

    override suspend fun saveItem(item: Item): Long {
        val dbItem = DbItem(
            item.id,
            item.site_id,
            item.title,
            item.price,
            item.currency_id,
            item.available_quantity,
            item.sold_quantity,
            item.condition,
            item.thumbnail,
            item.address.city_name,
            item.shipping.free_shipping,
            item.shipping.store_pick_up
        )
        return localDataSource.saveItem(dbItem)
    }
}