package me.kire.re.homestuffapp.presentation.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import me.kire.re.homestuffapp.domain.model.CategoryWithItemCount
import me.kire.re.homestuffapp.domain.usecases.category.GetCategories
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getCategoriesUseCase: GetCategories,
) : ViewModel() {
    private val _state = mutableStateOf(
        HomeState()
    )
    val state: State<HomeState> = _state

    init {
        getCategories()
    }

    val categoriesWithCount: StateFlow<List<CategoryWithItemCount>> =
        getCategoriesUseCase()
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.OnSearchTextChange -> onSearchTextChange(event.newText)
            is HomeEvent.OnErrorDismissed -> clearError()
            is HomeEvent.OnError -> setError(event.message)
        }
    }

    private fun onSearchTextChange(newText: String) {
        _state.value = _state.value.copy(
            searchText = newText
        )
    }

    private fun getCategories() {
        getCategoriesUseCase().onEach { categories ->
            println("categories = $categories")
            _state.value = _state.value.copy(
                categories = categories
            )
        }.launchIn(viewModelScope)
    }

    private fun clearError() {
        _state.value = _state.value.copy(
            error = null
        )
    }

    private fun setError(message: String) {
        _state.value = _state.value.copy(error = message)
    }
}