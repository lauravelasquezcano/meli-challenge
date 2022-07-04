package com.lauravelasquezcano.melichallenge.data.remote

import com.lauravelasquezcano.melichallenge.domain.SearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApi {

    @GET("/sites/MCO/search")
    suspend fun getAllItems(
        @Query("q") item: String
    ): Response<SearchResponse>
}