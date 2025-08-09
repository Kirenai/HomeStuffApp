package me.re.homestuffapp.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.re.homestuffapp.data.repository.ProductRepositoryAdapter
import me.re.homestuffapp.data.repository.PurchaseRepository
import me.re.homestuffapp.domain.port.ProductRepositoryPort
import me.re.homestuffapp.domain.port.PurchaseRepositoryPort
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindProductRepository(
        productRepositoryAdapter: ProductRepositoryAdapter
    ): ProductRepositoryPort

    @Binds
    @Singleton
    abstract fun bindPurchaseRepository(
        purchaseRepository: PurchaseRepository
    ): PurchaseRepositoryPort
}