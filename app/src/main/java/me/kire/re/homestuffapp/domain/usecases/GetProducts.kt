package me.kire.re.homestuffapp.domain.usecases

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import me.kire.re.homestuffapp.domain.model.Product
import me.kire.re.homestuffapp.domain.port.ProductRepositoryPort
import javax.inject.Inject

class GetProducts @Inject constructor(
    private val productRepositoryPort: ProductRepositoryPort
) {
    operator fun invoke(categoryId: Long?): Flow<PagingData<Product>> {
        return this.productRepositoryPort.getProducts()
    }
}