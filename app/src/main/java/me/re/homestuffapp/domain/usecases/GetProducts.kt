package me.re.homestuffapp.domain.usecases

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import me.re.homestuffapp.domain.model.Product
import me.re.homestuffapp.domain.port.ProductRepositoryPort
import javax.inject.Inject

class GetProducts @Inject constructor(
    private val productRepositoryPort: ProductRepositoryPort
) {
    operator fun invoke(categoryId: Long?): Flow<PagingData<Product>> {
        println("categoryId GP: $categoryId")
        if (categoryId != null) {
            return this.productRepositoryPort.getProductsByCategoryId(categoryId = categoryId)
        } else {
            // TODO: select all products when categoryId is null, as default behavior
            throw IllegalArgumentException("Category ID cannot be null")
        }
    }
}