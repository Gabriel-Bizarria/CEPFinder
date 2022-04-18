package com.cepfinder.cepfinder.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.cepfinder.cepfinder.R
import com.cepfinder.cepfinder.databinding.FragmentBottomBinding
import com.cepfinder.cepfinder.models.CepResponse
import com.cepfinder.cepfinder.ui.viewmodel.MainViewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapFragment
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.haroldadmin.cnradapter.NetworkResponse
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BottomFragment() : BottomSheetDialogFragment() {
    private var _binding: FragmentBottomBinding? = null
    val binding get() = _binding!!

    private val viewModel: MainViewModel by activityViewModels()

    private lateinit var mapFragment: SupportMapFragment

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBottomBinding.inflate(inflater, container, false)

        mapFragment =
            childFragmentManager.findFragmentById(R.id.maps_fragment) as SupportMapFragment

        return binding.root
    }

    override fun onResume() {
        super.onResume()

        viewModel.addressLiveData.observe(viewLifecycleOwner) { response ->
            if(response.address != "N/A"){
                binding.errorConstraint.visibility = View.GONE
                binding.mainConstraint.visibility = View.VISIBLE
                binding.cepTv.text = response.cep
                binding.cityTv.text = response.city
                binding.stateTv.text = response.state
                binding.streetTv.text = response.address

                mapFragment.getMapAsync { map ->
                    addMarkers(map, response)
                }
            }else{
                binding.progressBar.visibility = View.GONE
                binding.phraseError.visibility = View.VISIBLE
            }
        }
    }

    private fun addMarkers(map: GoogleMap, localization: CepResponse) {
        val latLng = LatLng(localization.lat.toDouble(), localization.lng.toDouble())
        Log.v("LAT_LNG", "${localization.lat}, ${localization.lng}")
        map.addMarker(
            MarkerOptions()
                .position(latLng)
                .title(localization.cep)
                .snippet("${localization.address}, ${localization.city}, ${localization.state}")
        )
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16f))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}