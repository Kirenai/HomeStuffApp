package me.re.homestuffapp.data.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import me.re.homestuffapp.data.dao.converter.UnitTypeConvertor
import me.re.homestuffapp.data.entity.CategoryEntity
import me.re.homestuffapp.data.entity.ProductEntity
import me.re.homestuffapp.data.entity.PurchaseEntity

@Database(
    entities = [CategoryEntity::class, ProductEntity::class, PurchaseEntity::class],
    version = 6,
    exportSchema = false,
)
@TypeConverters(
    value = [
        UnitTypeConvertor::class,
    ]
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun categoryDao(): CategoryDao
    abstract fun productDao(): ProductDao
    abstract fun purchaseDao(): PurchaseDao
}