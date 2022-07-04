package com.lauravelasquezcano.melichallenge.app.di.module

import com.lauravelasquezcano.melichallenge.data.repository.ItemsRepositoryImpl
import com.lauravelasquezcano.melichallenge.data.source.ItemsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@InstallIn(ActivityComponent::class)
@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindItemsRepository(repositoryImpl: ItemsRepositoryImpl): ItemsRepository
}