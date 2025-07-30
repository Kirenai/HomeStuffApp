package me.kire.re.homestuffapp.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.kire.re.homestuffapp.data.repository.ProductRepositoryAdapter
import me.kire.re.homestuffapp.domain.port.ProductRepositoryPort
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindProductRepository(
        productRepositoryAdapter: ProductRepositoryAdapter
    ): ProductRepositoryPort
}