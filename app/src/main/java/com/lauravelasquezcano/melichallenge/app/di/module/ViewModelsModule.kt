package com.lauravelasquezcano.melichallenge.app.di.module

import com.lauravelasquezcano.melichallenge.app.ui.main.details.DetailViewModel
import com.lauravelasquezcano.melichallenge.app.ui.main.results.ResultsViewModel
import com.lauravelasquezcano.melichallenge.usecases.GetItemUseCase
import com.lauravelasquezcano.melichallenge.usecases.SaveItemUseCase
import com.lauravelasquezcano.melichallenge.usecases.SearchItemsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@InstallIn(ActivityComponent::class)
@Module
object ViewModelsModule {

    @Provides
    fun providesResultViewModel(searchItemsUseCase: SearchItemsUseCase, saveItemUseCase: SaveItemUseCase) =
        ResultsViewModel(searchItemsUseCase, saveItemUseCase)

    @Provides
    fun providesDetailViewModel(getItemUseCase: GetItemUseCase) =
        DetailViewModel(getItemUseCase)
}