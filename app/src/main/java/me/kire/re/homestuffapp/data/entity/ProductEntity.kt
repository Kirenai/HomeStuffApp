package me.kire.re.homestuffapp.data.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import me.kire.re.homestuffapp.domain.model.enums.UnitType

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
    @PrimaryKey(autoGenerate = true) val productId: Long = 0,
    val name: String,
    val description: String,
    val imageUrl: String? = null,
    val categoryId: Long,
    val isAvailable: Boolean = true,
    val amountPerUnit: Double = 1.0,
    val unit: UnitType,
)