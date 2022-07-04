package com.lauravelasquezcano.melichallenge.domain

data class Item(
    val id: String,
    val site_id: String,
    val title: String,
    val price: Int,
    val currency_id: String,
    val available_quantity: Int,
    val sold_quantity: Int,
    val condition: String,
    val thumbnail: String,
    val address: Address,
    val shipping: Shipping,
    val original_price: Int
)
