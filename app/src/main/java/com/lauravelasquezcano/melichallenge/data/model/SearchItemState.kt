package com.lauravelasquezcano.melichallenge.data.model

import com.lauravelasquezcano.melichallenge.domain.Item

sealed class SearchItemState {
    object Loading : SearchItemState()
    data class Success(val items: List<Item>) : SearchItemState()
    data class Failure(val message: String) : SearchItemState()
}
