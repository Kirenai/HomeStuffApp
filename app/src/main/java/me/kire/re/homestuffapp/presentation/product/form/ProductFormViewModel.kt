package me.kire.re.homestuffapp.presentation.product.form

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import me.kire.re.homestuffapp.domain.model.Product
import me.kire.re.homestuffapp.domain.model.enums.UnitType
import me.kire.re.homestuffapp.presentation.product.form.ProductFormEvent.DescriptionChanged
import me.kire.re.homestuffapp.presentation.product.form.ProductFormEvent.NameChanged
import me.kire.re.homestuffapp.presentation.product.form.ProductFormEvent.SaveProduct
import javax.inject.Inject

@HiltViewModel
class ProductFormViewModel @Inject constructor(
    private val saveProduct: me.kire.re.homestuffapp.domain.usecases.SaveProduct
) : ViewModel() {

    private val _state: MutableStateFlow<ProductFormState> =
        MutableStateFlow(ProductFormState())
    val state: MutableStateFlow<ProductFormState> = _state

    fun onEvent(event: ProductFormEvent) {
        when (event) {
            is SaveProduct -> saveNourishment(event.categoryId)
            is NameChanged -> updateName(event.name)
            is DescriptionChanged -> updateDescription(event.description)
            is ProductFormEvent.UnitOnChanged -> updateUnit(event.unitType)
            is ProductFormEvent.AmountOnChanged -> updateAmount(event.amount)
        }
    }

    private fun updateName(name: String) {
        _state.update { it.copy(name = name) }
    }

    private fun updateDescription(description: String) {
        _state.update { it.copy(description = description) }
    }

    private fun updateAmount(amount: String) {
        _state.update { it.copy(amountPerUnit = amount.toDouble()) }
    }

    private fun updateUnit(unitType: UnitType) {
        _state.update { it.copy(unit = unitType) }
    }

    private fun saveNourishment(categoryId: Long) {
        val productState = _state.value
        viewModelScope.launch {
            saveProduct.invoke(
                Product(
                    name = productState.name,
                    description = productState.description,
                    amountPerUnit = productState.amountPerUnit,
                    unit = productState.unit,
                    categoryId = categoryId
                )
            )
            println(productState)
            println("Nourishment saved: ${productState.name}, ${productState.description}")
        }
    }
}