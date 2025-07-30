package me.kire.re.homestuffapp.domain.port

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import me.kire.re.homestuffapp.data.remote.dto.CreateNourishmentRequest
import me.kire.re.homestuffapp.domain.model.Product

interface ProductRepositoryPort {
    fun getProducts(): Flow<PagingData<Product>>

    fun getProductsByCategoryId(categoryId: Long): Flow<PagingData<Product>>

    suspend fun saveProduct(request: CreateNourishmentRequest)
}