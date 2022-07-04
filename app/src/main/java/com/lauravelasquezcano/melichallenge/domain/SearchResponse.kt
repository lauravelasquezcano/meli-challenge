package com.lauravelasquezcano.melichallenge.domain

data class SearchResponse(
    val site_id: String,
    val query: String,
    val results: List<Item>
)
