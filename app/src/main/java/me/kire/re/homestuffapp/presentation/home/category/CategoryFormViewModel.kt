package me.kire.re.homestuffapp.presentation.home.category

import android.database.sqlite.SQLiteConstraintException
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import me.kire.re.homestuffapp.domain.model.Category
import me.kire.re.homestuffapp.domain.usecases.category.UpsertCategory
import javax.inject.Inject

@HiltViewModel
class CategoryFormViewModel @Inject constructor(
    private val upsertCategory: UpsertCategory
) : ViewModel() {
    private val _state: MutableStateFlow<CategoryState> =
        MutableStateFlow(CategoryState())
    val state: MutableStateFlow<CategoryState> = _state

    fun onEvent(event: CategoryFormEvent) {
        when (event) {
            is CategoryFormEvent.OnSaveCategory -> {
                viewModelScope.launch {
                    onSaveCategory(event.category)
                }
            }

            is CategoryFormEvent.ClearSuccess -> {
                clearSuccess()
            }
        }
    }

    private suspend fun onSaveCategory(category: Category) {
        try {
            upsertCategory.invoke(category = category)
            _state.value = _state.value.copy(
                success = true,
                error = null
            )
        } catch (e: SQLiteConstraintException) {
            _state.value = _state.value.copy(
                error = "The category already exists.",
                success = false
            )
        }
    }

    private fun clearSuccess() {
        _state.value = _state.value.copy(
            success = false,
            error = null
        )
    }
}