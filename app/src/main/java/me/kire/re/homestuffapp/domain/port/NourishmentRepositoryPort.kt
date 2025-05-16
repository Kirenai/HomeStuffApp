package me.kire.re.homestuffapp.domain.port

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import me.kire.re.homestuffapp.domain.model.Nourishment

interface NourishmentRepositoryPort {
    fun getNourishments(): Flow<PagingData<Nourishment>>
}