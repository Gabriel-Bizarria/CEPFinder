package com.cepfinder.cepfinder.core.repository

import com.cepfinder.cepfinder.core.api.ApiService
import com.cepfinder.cepfinder.models.CepResponse
import com.haroldadmin.cnradapter.NetworkResponse
import javax.inject.Inject

class Repository @Inject constructor(private val apiService: ApiService): IRepository {

    override suspend fun getAdressCode(cep: String): NetworkResponse<CepResponse, String> {
        return apiService.getAddress(cep)
    }

}