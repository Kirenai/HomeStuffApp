package me.re.homestuffapp.presentation.product

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import me.re.homestuffapp.domain.model.Product
import me.re.homestuffapp.domain.usecases.DeleteProduct
import me.re.homestuffapp.domain.usecases.GetProducts
import me.re.homestuffapp.presentation.product.form.ProductEvent
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    getProducts: GetProducts,
    private val deleteProduct: DeleteProduct,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val categoryId = savedStateHandle.get<String>("categoryId")?.toLongOrNull()

    val nourishments: Flow<PagingData<Product>> = getProducts.invoke(categoryId = categoryId)
        .cachedIn(viewModelScope)

    fun onEvent(event: ProductEvent) {
        when (event) {
            is ProductEvent.OnDeleteProduct -> {
                viewModelScope.launch {
                    delete(event.productId)
                }
            }
        }
    }

    private suspend fun delete(productId: Long?) {
        this.deleteProduct.invoke(productId = productId)
    }
}