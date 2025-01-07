package com.github.usandbox.fetchcodingexercise.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.usandbox.fetchcodingexercise.data.FetchRepository
import com.github.usandbox.fetchcodingexercise.data.Item
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val repository: FetchRepository
) : ViewModel() {

    private val _viewState = MutableStateFlow<ViewState>(ViewState.Loading)
    val viewState: StateFlow<ViewState> = _viewState

    init {
        loadItems()
    }

    private fun loadItems() {
        viewModelScope.launch {
            val result = repository.getItems()
            if (result.isSuccess) {
                _viewState.value = ViewState.Success(
                    result.getOrDefault(emptyList())
                )
            } else {
                _viewState.value = ViewState.Error(
                    result.exceptionOrNull()?.message ?: "Unknown error"
                )
            }
        }
    }
}

sealed class ViewState {
    data object Loading : ViewState()
    data class Success(val items: List<Item>) : ViewState()
    data class Error(val message: String) : ViewState()
}
