package me.kire.re.homestuffapp.data.remote

import me.kire.re.homestuffapp.data.remote.dto.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface HomeStuffApi {
    @GET("/products/v0/nourishments")
    suspend fun getNourishments(
        @Query("page") page: Int?,
        @Query("size") size: Int?,
        @Query("sort") sort: String?,
        @Query("isAvailable") isAvailable: Boolean?,
    ): Response
}