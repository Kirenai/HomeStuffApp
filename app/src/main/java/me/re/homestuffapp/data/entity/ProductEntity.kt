package me.re.homestuffapp.data.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import me.re.homestuffapp.domain.model.Product
import me.re.homestuffapp.domain.model.enums.UnitType

@Entity(
    tableName = "products",
    foreignKeys = [
        ForeignKey(
            entity = CategoryEntity::class,
            parentColumns = ["categoryId"],
            childColumns = ["categoryId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index(
            value =  ["name"],
            unique = true
        ),
        Index(
            value = ["categoryId"]
        )
    ]
)
data class ProductEntity(
    @PrimaryKey(autoGenerate = true) val productId: Long? = null,
    val name: String,
    val description: String,
    val imageUrl: String? = null,
    val categoryId: Long,
    val isAvailable: Boolean = true,
    val amountPerUnit: Float = 1.0f,
    val unit: UnitType,
) {
    fun toDomainModel(): Product {
        return Product(
            productId = productId,
            name = name,
            description = description,
            imageUrl = imageUrl,
            isAvailable = isAvailable,
            amountPerUnit = amountPerUnit,
            unit = unit,
            categoryId = categoryId
        )
    }
}