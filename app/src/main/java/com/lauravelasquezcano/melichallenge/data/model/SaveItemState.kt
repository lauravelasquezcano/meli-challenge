package com.lauravelasquezcano.melichallenge.data.model

sealed class SaveItemState {
    object Success : SaveItemState()
    object Failure : SaveItemState()
}
