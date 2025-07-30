package me.kire.re.homestuffapp.data.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "purchases",
    foreignKeys = [
        ForeignKey(
            entity = ProductEntity::class,
            parentColumns = ["productId"],
            childColumns = ["productId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("productId")]
)
data class PurchaseEntity(
    @PrimaryKey(autoGenerate = true) val purchaseId: Long = 0,
    val productId: Long,
    val storeName: String,
    val weightKg: Float,
    val price: Float,
    val timestamp: Long = System.currentTimeMillis()
)