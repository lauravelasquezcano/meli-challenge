package com.lauravelasquezcano.melichallenge.app.di.module

import android.app.Application
import androidx.room.Room
import com.lauravelasquezcano.melichallenge.app.database.ItemDataSource
import com.lauravelasquezcano.melichallenge.app.database.MeliDatabase
import com.lauravelasquezcano.melichallenge.data.source.LocalDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Singleton
    @Provides
    fun localDataSourceProvider(db: MeliDatabase): LocalDataSource =
        ItemDataSource(db.itemDao())

    @Singleton
    @Provides
    fun databaseProvider(app: Application) =
        Room.databaseBuilder(app, MeliDatabase::class.java, "Mercadolibre.db").build()

}