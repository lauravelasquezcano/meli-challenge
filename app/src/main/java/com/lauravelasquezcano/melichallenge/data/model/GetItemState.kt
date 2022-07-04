package com.lauravelasquezcano.melichallenge.data.model

import com.lauravelasquezcano.melichallenge.app.database.DbItem

sealed class GetItemState {
    object Loading: GetItemState()
    data class Success(val item: DbItem) : GetItemState()
    object Failure : GetItemState()
}
