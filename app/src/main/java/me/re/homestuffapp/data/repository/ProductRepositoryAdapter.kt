package me.re.homestuffapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import me.re.homestuffapp.data.dao.ProductDao
import me.re.homestuffapp.data.entity.ProductEntity
import me.re.homestuffapp.data.remote.HomeStuffApi
import me.re.homestuffapp.data.remote.ProductPagingSource
import me.re.homestuffapp.domain.model.Product
import me.re.homestuffapp.domain.port.ProductRepositoryPort
import me.re.homestuffapp.util.mapper.toProductEntity
import me.re.homestuffapp.util.toCreateNourishmentRequest
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
            productDao.getProductsByCategory(categoryId)
        }.flow.map {
            it.map { productEntity: ProductEntity ->
                productEntity.toDomainModel()
            }
        }
    }

    override suspend fun saveProduct(product: Product) {
        val request = toCreateNourishmentRequest(product = product)
        nourishmentApis.saveProduct(userId = "1", categoryId = "5", nourishment = request)
    }

    override suspend fun saveProductRoom(product: Product) {
        val productEntity = toProductEntity(product = product)
        println("Saving product to Room: $productEntity")
        this.productDao.insert(productEntity)
    }

    override suspend fun deleteProduct(productId: Long) {
        this.productDao.deleteProduct(productId = productId)
    }

    override suspend fun updateProduct(product: Product) {
        val productEntity: ProductEntity = toProductEntity(product = product)
        this.productDao.updateProducts(products = listOf(productEntity))
    }
}