package com.cepfinder.cepfinder.core.repository

import com.cepfinder.cepfinder.models.CepResponse
import com.haroldadmin.cnradapter.NetworkResponse
import retrofit2.Call

interface IRepository {

    suspend fun getAdressCode(cep: String): NetworkResponse<CepResponse, String>
}