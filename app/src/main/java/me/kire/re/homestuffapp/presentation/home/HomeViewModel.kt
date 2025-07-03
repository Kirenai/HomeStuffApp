package me.kire.re.homestuffapp.presentation.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import me.kire.re.homestuffapp.domain.model.categories
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {
    private val _state = mutableStateOf(
        HomeState(
            categories = categories
        )
    )
    val state: State<HomeState> = _state

    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.OnSearchTextChange -> onSearchTextChange(event.newText)
        }
    }

    private fun onSearchTextChange(newText: String) {
        _state.value = _state.value.copy(
            searchText = newText
        )
    }
}