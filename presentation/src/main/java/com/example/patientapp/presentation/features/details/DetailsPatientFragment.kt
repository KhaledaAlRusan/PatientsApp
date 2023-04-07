package com.example.patientapp.presentation.features.details

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.patientapp.presentation.databinding.FragmentDetailsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailsPatientFragment : Fragment(){

    private lateinit var binding: FragmentDetailsBinding
    private val viewModel:DetailsViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentDetailsBinding.inflate(layoutInflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObserver()
    }

    private fun initObserver() {
        lifecycleScope.launch {
            viewModel.detailsStateFlow.collect {
                if (it != null) {
                    Toast.makeText(requireContext(),
                    it.toString(),
                    Toast.LENGTH_SHORT).show()
                }
            }
        }

        lifecycleScope.launch {
            viewModel.detailsLoadingStateFlow.collect {
                binding.progressCircular.isVisible = it
            }
        }

        lifecycleScope.launch {
            viewModel.detailsErrorStateFlow.collect {
                if (it != null) {
                    Log.d("TAGGG", it.toString())
                }

            }
        }

    }
}