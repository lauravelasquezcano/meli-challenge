package com.lauravelasquezcano.melichallenge.data.source

import com.lauravelasquezcano.melichallenge.app.database.DbItem

interface LocalDataSource {

    fun saveItem(item: DbItem): Long
}