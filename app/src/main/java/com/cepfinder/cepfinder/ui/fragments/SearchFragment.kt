package com.cepfinder.cepfinder.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.cepfinder.cepfinder.databinding.FragmentSearchBinding
import com.cepfinder.cepfinder.models.CepResponse
import com.cepfinder.cepfinder.ui.viewmodel.MainViewModel
import okio.utf8Size

class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.btSearch.setOnClickListener {
            val cep = binding.cepInput.text.toString()
            if(!binding.cepInput.text.isNullOrEmpty() && cep.length == 8){
                viewModel.contactApi(cep)
            }else{
                Toast.makeText(requireContext(), "CEP Invalid", Toast.LENGTH_SHORT).show()
            }
        }

    }

    override fun onResume() {
        super.onResume()
        viewModel.addressLiveData.observe(viewLifecycleOwner){
            var bottomSheet = BottomFragment()
            bottomSheet.show(childFragmentManager, "OPENED_BOTTOM_SHEET")
        }

        viewModel.errorLiveData.observe(viewLifecycleOwner){
            Toast.makeText(
                requireContext(),
                "CEP não encontrado ou não existe",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}