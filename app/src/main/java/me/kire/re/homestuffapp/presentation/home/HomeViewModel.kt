package me.kire.re.homestuffapp.presentation.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onEach
import me.kire.re.homestuffapp.domain.model.categories
import me.kire.re.homestuffapp.domain.usecases.category.GetCategories
import me.kire.re.homestuffapp.domain.usecases.category.UpsertCategory
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val upsertCategory: UpsertCategory,
    private val getCategoriesUseCase: GetCategories
) : ViewModel() {
    private val _state = mutableStateOf(
        HomeState(
            categories = categories
        )
    )
    val state: State<HomeState> = _state

//    init {
//        getCategories()
//    }

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

//    private fun getCategories() {
//        getCategoriesUseCase().onEach { categories ->
//            _state.value = _state.value.copy(
//                categories = categories
//            )
//        }
//    }
}