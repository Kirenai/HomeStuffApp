package me.re.homestuffapp.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.re.homestuffapp.data.dao.AppDatabase
import me.re.homestuffapp.data.dao.CategoryDao
import me.re.homestuffapp.data.dao.ProductDao
import me.re.homestuffapp.data.dao.PurchaseDao
import me.re.homestuffapp.data.dao.migration.MIGRATION_4_5
import me.re.homestuffapp.data.dao.migration.MIGRATION_5_6
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    @Provides
    @Singleton
    fun provideAppDatabase(
        application: Application
    ): AppDatabase {
        return Room.databaseBuilder(
            context = application,
            AppDatabase::class.java,
            "home_stuff_database"
        )
            .addMigrations(
                MIGRATION_4_5, MIGRATION_5_6
            )
            .build()
    }

    @Provides
    @Singleton
    fun provideCategoryDao(database: AppDatabase): CategoryDao = database.categoryDao()

    @Provides
    @Singleton
    fun provideProductDao(database: AppDatabase): ProductDao = database.productDao()

    @Provides
    @Singleton
    fun providePurchaseDao(database: AppDatabase): PurchaseDao = database.purchaseDao()
}