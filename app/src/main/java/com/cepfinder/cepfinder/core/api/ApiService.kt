package com.cepfinder.cepfinder.core.api

import com.cepfinder.cepfinder.models.CepResponse
import com.haroldadmin.cnradapter.NetworkResponse
import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("/json/{cep}")
    suspend fun getAddress(
        @Path("cep")cep: String
    ): NetworkResponse<CepResponse, String>

}