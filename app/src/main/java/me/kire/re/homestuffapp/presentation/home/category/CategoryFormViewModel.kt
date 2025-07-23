package me.kire.re.homestuffapp.presentation.home.category

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import me.kire.re.homestuffapp.domain.usecases.category.UpsertCategory
import javax.inject.Inject

@HiltViewModel
class CategoryFormViewModel @Inject constructor(
    private val upsertCategory: UpsertCategory
) : ViewModel() {
    fun onEvent(event: CategoryFormEvent) {
        when (event) {
            is CategoryFormEvent.OnSaveCategory -> {
                viewModelScope.launch {
                    onSaveCategory(event.name)
                }
            }
        }
    }

    private suspend fun onSaveCategory(name: String) {
        upsertCategory.invoke(name)
    }
}