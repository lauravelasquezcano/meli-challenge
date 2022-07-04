package com.lauravelasquezcano.melichallenge.app.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [DbItem::class], version = 1, exportSchema = false)
abstract class MeliDatabase : RoomDatabase() {
    abstract fun itemDao(): ItemDao
}