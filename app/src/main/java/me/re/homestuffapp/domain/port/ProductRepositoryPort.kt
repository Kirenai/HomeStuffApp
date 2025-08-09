package me.re.homestuffapp.domain.port

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import me.re.homestuffapp.domain.model.Product

interface ProductRepositoryPort {
    fun getProducts(): Flow<PagingData<Product>>

    fun getProductsByCategoryId(categoryId: Long): Flow<PagingData<Product>>

    suspend fun saveProduct(product: Product)

    suspend fun saveProductRoom(product: Product)

    suspend fun deleteProduct(productId: Long)
}