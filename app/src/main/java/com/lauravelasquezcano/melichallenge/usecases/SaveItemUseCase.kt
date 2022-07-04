package com.lauravelasquezcano.melichallenge.usecases

import com.lauravelasquezcano.melichallenge.data.source.ItemsRepository
import com.lauravelasquezcano.melichallenge.domain.Item
import javax.inject.Inject

class SaveItemUseCase @Inject constructor(private val repository: ItemsRepository) {

    suspend fun execute(item: Item) = repository.saveItem(item)
}