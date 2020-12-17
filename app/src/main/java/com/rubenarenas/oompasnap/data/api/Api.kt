package com.rubenarenas.oompasnap.data.api

import com.rubenarenas.oompasnap.data.datamodels.OompaData
import com.rubenarenas.oompasnap.data.datamodels.OompaResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {
    companion object {
        const val BASE_URL = "https://2q2woep105.execute-api.eu-west-1.amazonaws.com/napptilus/"
    }

    @GET("oompa-loompas")
    suspend fun getOompaLoompas(
        @Query("page") page: Int
    ): OompaResponse

    @GET("oompa-loompas/{id}")
     suspend fun getOompaLoompaById(
        @Path("id") id: Int
    ): OompaData
}