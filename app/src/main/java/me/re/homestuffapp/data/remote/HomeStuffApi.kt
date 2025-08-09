package me.re.homestuffapp.data.remote

import me.re.homestuffapp.data.remote.dto.CreateNourishmentRequest
import me.re.homestuffapp.data.remote.dto.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface HomeStuffApi {
    @GET("/products/v0/nourishments")
    suspend fun getProducts(
        @Query("page") page: Int?,
        @Query("size") size: Int?,
        @Query("sort") sort: String?,
        @Query("isAvailable") isAvailable: Boolean?,
    ): Response

    @POST("/products/v0/nourishments/user/{userId}/category/{categoryId}")
    suspend fun saveProduct(
        @Path("userId") userId: String,
        @Path("categoryId") categoryId: String,
        @Body nourishment: CreateNourishmentRequest
    )
}