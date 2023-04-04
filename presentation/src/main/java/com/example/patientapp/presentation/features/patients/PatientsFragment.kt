package com.example.patientapp.presentation.features.patients

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.patientapp.presentation.R
import com.example.patientapp.presentation.databinding.PatientsFragmentBinding
import com.example.patientapp.presentation.features.patients.adapters.PatientsAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PatientsFragment:Fragment() {

    private lateinit var binding: PatientsFragmentBinding
    private val viewModel: PatientsViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = PatientsFragmentBinding.inflate(layoutInflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObserver()
        initListener()
    }

    private fun initListener() {
        binding.fabAdd.setOnClickListener {
            Log.d("TAGGG", "didn't Enter the fragment")
            findNavController().navigate(R.id.action_patientsFragment_to_addPatientFragment)
        }
    }

    private fun initObserver() {
        lifecycleScope.launch {
            viewModel.patientsStateFlow.collect {
                if (!it.isNullOrEmpty()) {
                    binding.recyclerView.adapter = PatientsAdapter(it)
                }
            }
        }

        lifecycleScope.launch {
            viewModel.patientsLoadingStateFlow.collect {
                binding.progressCircular.isVisible = it
            }
        }

        lifecycleScope.launch {
            viewModel.patientsErrorStateFlow.collect {
                if (it != null) {
                    Log.d("TAGGG", it.toString())
                }

            }
        }
    }

}