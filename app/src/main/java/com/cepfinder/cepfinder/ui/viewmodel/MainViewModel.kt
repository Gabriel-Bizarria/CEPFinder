package com.cepfinder.cepfinder.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cepfinder.cepfinder.core.repository.Repository
import com.cepfinder.cepfinder.extensions.SingleLiveEvent
import com.cepfinder.cepfinder.models.CepResponse
import com.haroldadmin.cnradapter.NetworkResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val _addressLiveData = SingleLiveEvent<CepResponse>()
    val addressLiveData: LiveData<CepResponse> = _addressLiveData

    fun contactApi(cep: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.getAdressCode(cep)
            if (result is NetworkResponse.Success) {
                _addressLiveData.postValue(result.body!!)
                Log.v("API_RESPONSE", "${result.body}")
            }else{
                val error = CepResponse("N/A",
                    "N/A",
                    "N/A",
                    "N/A",
                    "N/A",
                    "N/A",
                    "N/A",
                    "N/A",
                    "N/A",
                    "N/A",
                    cep)
                _addressLiveData.postValue(error)
            }
        }
    }
}