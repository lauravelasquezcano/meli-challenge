package com.lauravelasquezcano.melichallenge.usecases

import com.lauravelasquezcano.melichallenge.data.source.ItemsRepository
import javax.inject.Inject

class SearchItemsUseCase @Inject constructor(
    private val repository: ItemsRepository
) {
    suspend fun execute(query: String) = repository.searchItems(query)
}