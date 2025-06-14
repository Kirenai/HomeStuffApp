package me.kire.re.homestuffapp.domain.usecases

import me.kire.re.homestuffapp.data.remote.dto.CreateNourishmentRequest
import me.kire.re.homestuffapp.domain.port.NourishmentRepositoryPort
import javax.inject.Inject

class SaveNourishment @Inject constructor(
    private val repository: NourishmentRepositoryPort
) {
    suspend operator fun invoke(
        request: CreateNourishmentRequest
    ) {
        repository.saveNourishment(request)
    }
}