package com.lauravelasquezcano.melichallenge.usecases

import com.lauravelasquezcano.melichallenge.data.source.ItemsRepository
import javax.inject.Inject

class GetItemUseCase @Inject constructor(private val repository: ItemsRepository) {

    suspend fun execute(id: String) = repository.getItemById(id)
}