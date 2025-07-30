package me.kire.re.homestuffapp.data.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

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
    val categoryId: Long
)