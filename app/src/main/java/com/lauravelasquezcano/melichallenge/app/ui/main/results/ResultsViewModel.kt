package com.lauravelasquezcano.melichallenge.app.ui.main.results

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lauravelasquezcano.melichallenge.data.model.GetItemsState
import com.lauravelasquezcano.melichallenge.domain.ResultWrapper
import com.lauravelasquezcano.melichallenge.usecases.SearchItemsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class ResultsViewModel @Inject constructor(
    private val searchItemsUseCase: SearchItemsUseCase
) : ViewModel() {

    private val _getItemState = MutableLiveData<GetItemsState>()
    val getItemsState: LiveData<GetItemsState>
        get() = _getItemState

    fun searchItems(query: String) {
        _getItemState.postValue(GetItemsState.Loading)
        viewModelScope.launch(Dispatchers.IO) {
            when(val items = searchItemsUseCase.execute(query)) {
                is ResultWrapper.Success ->
                    _getItemState.postValue(GetItemsState.Success(items.data.results))
                is ResultWrapper.GenericError -> {
                    _getItemState.postValue(GetItemsState.Failure(items.message.toString()))
                }
            }
        }
    }
}