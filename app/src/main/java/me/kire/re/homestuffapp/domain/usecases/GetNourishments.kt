package me.kire.re.homestuffapp.domain.usecases

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import me.kire.re.homestuffapp.domain.model.Nourishment
import me.kire.re.homestuffapp.domain.port.NourishmentRepositoryPort
import javax.inject.Inject

class GetNourishments @Inject constructor(
    private val nourishmentRepositoryPort: NourishmentRepositoryPort
) {
    operator fun invoke(categoryId: Long?): Flow<PagingData<Nourishment>> {
        return this.nourishmentRepositoryPort.getNourishments()
    }
}