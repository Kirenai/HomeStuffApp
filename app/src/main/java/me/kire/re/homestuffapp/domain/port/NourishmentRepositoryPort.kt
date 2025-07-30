package me.kire.re.homestuffapp.domain.port

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import me.kire.re.homestuffapp.data.remote.dto.CreateNourishmentRequest
import me.kire.re.homestuffapp.domain.model.Nourishment

interface NourishmentRepositoryPort {
    fun getNourishments(): Flow<PagingData<Nourishment>>

    fun getProductsByCategoryId(categoryId: Long): Flow<PagingData<Nourishment>>

    suspend fun saveNourishment(request: CreateNourishmentRequest)
}