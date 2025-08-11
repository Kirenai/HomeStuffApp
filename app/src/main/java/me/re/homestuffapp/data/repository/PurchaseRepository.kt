package me.re.homestuffapp.data.repository

import kotlinx.coroutines.flow.Flow
import me.re.homestuffapp.data.dao.ProductDao
import me.re.homestuffapp.data.dao.PurchaseDao
import me.re.homestuffapp.data.entity.PurchaseEntity
import me.re.homestuffapp.domain.model.PurchasePriceAndMonth
import me.re.homestuffapp.domain.port.PurchaseRepositoryPort
import javax.inject.Inject

class PurchaseRepository @Inject constructor(
    private val purchaseDao: PurchaseDao,
    private val productDao: ProductDao
) : PurchaseRepositoryPort {
    override fun getLastTwoPurchases(productId: Long): Flow<List<PurchaseEntity>> {
        return this.purchaseDao.getLastTwoPurchases(productId = productId)
    }

    override fun getLast15Prices(productId: Long): Flow<List<PurchasePriceAndMonth>> {
        return this.purchaseDao.getLast15Prices(productId = productId)
    }

    override suspend fun insertAllPurchases(purchases: List<PurchaseEntity>) {
        println("Insert All Purchases: $purchases")
        this.synchronizePrice(purchases)
        return this.purchaseDao.insertAll(purchases = purchases)
    }

    private suspend fun synchronizePrice(purchases: List<PurchaseEntity>) {
        val ids = purchases.map {
            it.productId
        }
        val products = this.productDao.loadProductsFromIds(ids = ids)
        val newProducts = products.map { product ->
            val purchase =
                findPurchaseByProductId(productId = product.productId, purchases = purchases)
            product.copy(
                amountPerUnit = product.amountPerUnit.plus(other = purchase.weightKg)
            )
        }
        newProducts.forEach {
            println("New Product: $it")
        }
        this.productDao.updateProducts(products = newProducts)
    }

    private fun findPurchaseByProductId(
        productId: Long?,
        purchases: List<PurchaseEntity>
    ): PurchaseEntity {
        return purchases.firstOrNull {
            it.productId == productId
        } ?: throw IllegalStateException("No purchase found for product with id $productId")
    }
}