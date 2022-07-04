package com.lauravelasquezcano.melichallenge.data.model

import com.lauravelasquezcano.melichallenge.domain.Item

sealed class GetItemsState {
    object Loading : GetItemsState()
    data class Success(val items: List<Item>) : GetItemsState()
    data class Failure(val message: String) : GetItemsState()
}
