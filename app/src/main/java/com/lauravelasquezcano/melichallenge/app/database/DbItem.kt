package com.lauravelasquezcano.melichallenge.app.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "item")
data class DbItem(
    @PrimaryKey
    val id: String,
    val site_id: String,
    val title: String,
    val price: Int,
    val currency_id: String,
    val available_quantity: Int,
    val sold_quantity: Int,
    val condition: String,
    val thumbnail: String,
    val city_name: String,
    val free_shipping: Boolean,
    val store_pick_up: Boolean
)
