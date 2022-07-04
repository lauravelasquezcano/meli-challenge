package com.lauravelasquezcano.melichallenge.app.di.module

import com.lauravelasquezcano.melichallenge.app.ui.main.results.ResultsViewModel
import com.lauravelasquezcano.melichallenge.usecases.SearchItemsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import javax.inject.Singleton

@InstallIn(ActivityComponent::class)
@Module
object ViewModelsModule {

    @Provides
    fun providesResultViewModel(searchItemsUseCase: SearchItemsUseCase) =
        ResultsViewModel(searchItemsUseCase)
}