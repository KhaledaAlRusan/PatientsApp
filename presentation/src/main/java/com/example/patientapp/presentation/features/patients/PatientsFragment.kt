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
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PatientsFragment:Fragment() {

    private lateinit var binding: PatientsFragmentBinding
    private val viewModel: PatientsViewModel by viewModels()
    private lateinit var adapter: PatientsAdapter


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

        initAdapter()
        initObserver()
        initListener()
    }

    private fun initAdapter() {
        adapter = PatientsAdapter()
        binding.recyclerView.adapter = adapter
    }

    private fun initListener() {
        binding.fabAdd.setOnClickListener {
            Log.d("TAGGG", "didn't Enter the fragment")
            findNavController().navigate(R.id.action_patientsFragment_to_addPatientFragment)
        }

        binding.swipeRefresher.setOnRefreshListener {
            viewModel.getPatients()
            lifecycleScope.launch {
                delay(3000)
                binding.swipeRefresher.isRefreshing = false
            }
        }

    }

    private fun initObserver() {
        lifecycleScope.launch {
            viewModel.patientsStateFlow.collect {
                if (!it.isNullOrEmpty()) {
                    adapter.submitList(it)
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