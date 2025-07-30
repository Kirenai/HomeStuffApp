package me.kire.re.homestuffapp.presentation.product.form

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import me.kire.re.homestuffapp.presentation.product.form.ProductFormEvent.DescriptionChanged
import me.kire.re.homestuffapp.presentation.product.form.ProductFormEvent.NameChanged
import me.kire.re.homestuffapp.presentation.product.form.ProductFormEvent.ProductTypeChanged
import me.kire.re.homestuffapp.presentation.product.form.ProductFormEvent.SaveProduct
import me.kire.re.homestuffapp.util.toCreateNourishmentRequest
import javax.inject.Inject

@HiltViewModel
class ProductFormViewModel @Inject constructor(
    private val saveNourishment: me.kire.re.homestuffapp.domain.usecases.SaveNourishment
) : ViewModel() {

    private val _state: MutableStateFlow<ProductFormState> =
        MutableStateFlow(ProductFormState())
    val state: MutableStateFlow<ProductFormState> = _state

    fun onEvent(event: ProductFormEvent) {
        when (event) {
            is SaveProduct -> saveNourishment()
            is NameChanged -> updateName(event.name)
            is DescriptionChanged -> updateDescription(event.description)
            is ProductTypeChanged -> updateNourishmentType(event.nourishmentType, event.value)
        }
    }

    private fun updateName(name: String) {
        _state.update { it.copy(name = name) }
    }

    private fun updateDescription(description: String) {
        _state.update { it.copy(description = description) }
    }

    private fun updateNourishmentType(nourishmentType: String, value: String) {
        _state.update {
            it.copy(
                nourishmentType = nourishmentType,
                unit = if (nourishmentType == "UNIT") value else it.unit,
                percentage = if (nourishmentType == "PERCENTAGE") value else it.percentage
            )
        }
    }

    private fun saveNourishment() {
        val request = toCreateNourishmentRequest(state.value)
        viewModelScope.launch {
            saveNourishment.invoke(request)
            println(_state.value)
            println("Nourishment saved: ${_state.value.name}, ${_state.value.description}")
        }
    }
}