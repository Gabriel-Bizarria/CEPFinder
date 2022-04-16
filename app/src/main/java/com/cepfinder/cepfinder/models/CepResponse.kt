package com.cepfinder.cepfinder.models

import com.squareup.moshi.Json

data class CepResponse(

	@Json(name="city_ibge")
	val cityIbge: String,

	@Json(name="address")
	val address: String,

	@Json(name="address_type")
	val addressType: String,

	@Json(name="lng")
	val lng: String,

	@Json(name="city")
	val city: String,

	@Json(name="ddd")
	val ddd: String,

	@Json(name="district")
	val district: String,

	@Json(name="address_name")
	val addressName: String,

	@Json(name="state")
	val state: String,

	@Json(name="lat")
	val lat: String,

	@Json(name="cep")
	val cep: String
)
