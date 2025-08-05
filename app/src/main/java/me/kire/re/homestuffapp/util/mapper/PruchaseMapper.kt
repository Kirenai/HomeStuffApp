package me.kire.re.homestuffapp.util.mapper

import me.kire.re.homestuffapp.data.entity.PurchaseEntity
import me.kire.re.homestuffapp.domain.model.Shopping

fun toPurchaseEntity(shopping: Shopping): PurchaseEntity {
    return PurchaseEntity(
        productId = shopping.productId,
        storeName = shopping.store ?: "",
        weightKg = shopping.quantity?.toFloat() ?: 0.0f,
        price = shopping.price?.toFloat() ?: 0.0f
    )
}