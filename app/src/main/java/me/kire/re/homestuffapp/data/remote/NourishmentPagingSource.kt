package me.kire.re.homestuffapp.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import me.kire.re.homestuffapp.data.remote.dto.Response
import me.kire.re.homestuffapp.domain.model.Nourishment

class NourishmentPagingSource(
    private val api: HomeStuffApi
) : PagingSource<Int, Nourishment>() {
    override fun getRefreshKey(state: PagingState<Int, Nourishment>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Nourishment> {
        val nextPageNumber = params.key ?: 1
        return try {
            val response: Response =
                this.api.getNourishments(page = nextPageNumber, null, null, null)

            LoadResult.Page(
                data = response.content.map { it.toNourishment() },
                nextKey = if (response.last) null else nextPageNumber + 1,
                prevKey = if (response.first) null else nextPageNumber - 1,
            )
        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(e)
        }
    }
}