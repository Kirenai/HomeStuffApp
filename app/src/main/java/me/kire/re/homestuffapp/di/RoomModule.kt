package me.kire.re.homestuffapp.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.kire.re.homestuffapp.data.dao.AppDatabase
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
        ).build()
    }

    @Provides
    @Singleton
    fun provideCategoryDao(
        database: AppDatabase
    ) = database.categoryDao()
}