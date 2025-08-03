package me.kire.re.homestuffapp.util.mapper

import me.kire.re.homestuffapp.data.entity.ProductEntity
import me.kire.re.homestuffapp.domain.model.Product

fun toProductEntity(
    product: Product
): ProductEntity {
    return ProductEntity(
        name = product.name,
        description = product.description,
        imageUrl = product.imageUrl,
        isAvailable = product.isAvailable,
        amountPerUnit = product.amountPerUnit ?: 1.0,
        unit = product.unit,
        categoryId = product.categoryId,
    )
}