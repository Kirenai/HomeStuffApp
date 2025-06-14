package me.kire.re.homestuffapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import me.kire.re.homestuffapp.data.remote.HomeStuffApi
import me.kire.re.homestuffapp.data.remote.NourishmentPagingSource
import me.kire.re.homestuffapp.data.remote.dto.CreateNourishmentRequest
import me.kire.re.homestuffapp.domain.model.Nourishment
import me.kire.re.homestuffapp.domain.port.NourishmentRepositoryPort
import javax.inject.Inject

class NourishmentRepositoryAdapter @Inject constructor(
    private val nourishmentApis: HomeStuffApi,
) : NourishmentRepositoryPort {
    override fun getNourishments(): Flow<PagingData<Nourishment>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10
            )
        ) {
            NourishmentPagingSource(
                api = nourishmentApis
            )
        }.flow
    }

    override suspend fun saveNourishment(request: CreateNourishmentRequest) {
        nourishmentApis.saveNourishment(userId = "1", categoryId = "5", nourishment = request)
    }
}