package me.kire.re.homestuffapp.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.kire.re.homestuffapp.data.dao.AppDatabase
import me.kire.re.homestuffapp.data.dao.CategoryDao
import me.kire.re.homestuffapp.data.dao.ItemDao
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
//            .addMigrations(
//                Migration(1, 2) {
//                    // Migration logic from version 1 to 2
//                    // For example, adding a new column or table
//                },
//                Migration(2, 3) {
//                    // Migration logic from version 2 to 3
//                    // For example, modifying an existing table
//                }
//            )
            .build()
    }

    @Provides
    @Singleton
    fun provideCategoryDao(database: AppDatabase): CategoryDao = database.categoryDao()

    @Provides
    @Singleton
    fun provideItemDao(database: AppDatabase): ItemDao = database.itemDao()
}