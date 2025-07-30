package me.kire.re.homestuffapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import me.kire.re.homestuffapp.data.dao.ProductDao
import me.kire.re.homestuffapp.data.remote.HomeStuffApi
import me.kire.re.homestuffapp.data.remote.ProductPagingSource
import me.kire.re.homestuffapp.data.remote.dto.CreateNourishmentRequest
import me.kire.re.homestuffapp.domain.model.Product
import me.kire.re.homestuffapp.domain.port.ProductRepositoryPort
import javax.inject.Inject

class ProductRepositoryAdapter @Inject constructor(
    private val nourishmentApis: HomeStuffApi,
    private val productDao: ProductDao
) : ProductRepositoryPort {
    override fun getProducts(): Flow<PagingData<Product>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10
            )
        ) {
            ProductPagingSource(
                api = nourishmentApis
            )
        }.flow
    }

    override fun getProductsByCategoryId(categoryId: Long): Flow<PagingData<Product>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10
            ),
        ) {
            productDao.getItemsByCategory(categoryId)
        }.flow.map {
            it.map { productEntity ->
                Product(
                    productId = productEntity.productId.toString(),
                    name = productEntity.name,
                    description = productEntity.description,
                    imageUrl = "",
                    isAvailable = true
                )
            }
        }
    }

    override suspend fun saveProduct(request: CreateNourishmentRequest) {
        nourishmentApis.saveProduct(userId = "1", categoryId = "5", nourishment = request)
    }
}