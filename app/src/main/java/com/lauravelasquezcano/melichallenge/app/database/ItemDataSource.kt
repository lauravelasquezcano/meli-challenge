package com.lauravelasquezcano.melichallenge.app.database

import com.lauravelasquezcano.melichallenge.data.source.LocalDataSource

class ItemDataSource(private val dao: ItemDao) : LocalDataSource {

    override fun saveItem(item: DbItem): Long = dao.insert(item)
}