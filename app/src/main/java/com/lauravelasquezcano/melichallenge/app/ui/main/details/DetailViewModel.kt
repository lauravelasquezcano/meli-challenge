package com.lauravelasquezcano.melichallenge.app.ui.main.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lauravelasquezcano.melichallenge.data.model.GetItemState
import com.lauravelasquezcano.melichallenge.data.model.SearchItemState
import com.lauravelasquezcano.melichallenge.usecases.GetItemUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailViewModel @Inject constructor(
    private val getItemUseCase: GetItemUseCase
) : ViewModel() {

    private val _getItemState = MutableLiveData<GetItemState>()
    val getItemState: LiveData<GetItemState>
        get() = _getItemState

    fun getItemById(id: String){
        _getItemState.postValue(GetItemState.Loading)
        viewModelScope.launch(Dispatchers.IO) {
            val item = getItemUseCase.execute(id)
            if (item != null) {
                _getItemState.postValue(GetItemState.Success(item))
            } else {
                _getItemState.postValue(GetItemState.Failure)
            }
        }
    }
}