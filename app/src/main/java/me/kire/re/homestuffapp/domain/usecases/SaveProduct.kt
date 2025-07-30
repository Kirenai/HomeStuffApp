package me.kire.re.homestuffapp.domain.usecases

import me.kire.re.homestuffapp.data.remote.dto.CreateNourishmentRequest
import me.kire.re.homestuffapp.domain.port.ProductRepositoryPort
import javax.inject.Inject

class SaveProduct @Inject constructor(
    private val repository: ProductRepositoryPort
) {
    suspend operator fun invoke(
        request: CreateNourishmentRequest
    ) {
        repository.saveProduct(request)
    }
}