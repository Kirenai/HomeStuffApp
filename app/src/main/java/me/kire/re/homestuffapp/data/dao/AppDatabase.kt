package me.kire.re.homestuffapp.data.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import me.kire.re.homestuffapp.data.entity.CategoryEntity
import me.kire.re.homestuffapp.data.entity.ProductEntity
import me.kire.re.homestuffapp.data.entity.PurchaseEntity

@Database(
    entities = [CategoryEntity::class, ProductEntity::class, PurchaseEntity::class],
    version = 5,
    exportSchema = false,
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun categoryDao(): CategoryDao
    abstract fun productDao(): ProductDao
    abstract fun purchaseDao(): PurchaseDao
}