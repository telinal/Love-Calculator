package com.example.lovecalculator.api

import com.example.lovecalculator.models.PercentageData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface PercentageAPI {

    @GET("/getPercentage")
    suspend fun getPercent(
        @Header("x-rapidapi-key") apiKey:String = "04822bb627msh66ca29260c06c79p13eccajsn268207619e4c",
        @Query("fname") fname : String,
        @Query("sname") sname : String
    ): Response<PercentageData>

}