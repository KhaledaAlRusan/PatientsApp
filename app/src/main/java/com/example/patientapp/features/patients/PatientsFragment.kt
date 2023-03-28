package com.example.patientapp.features.patients

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.patientapp.databinding.PatientsFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PatientsFragment:Fragment() {

    private lateinit var binding: PatientsFragmentBinding
    private val viewModel:PatientsViewModel by viewModels()


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

        lifecycleScope.launch {
            viewModel.patientsStateFlow.collect{
                if(!it.isNullOrEmpty()){
                    Toast.makeText(requireContext(),it.toString(),Toast.LENGTH_LONG).show()
                }
            }
        }

        lifecycleScope.launch {
            viewModel.patientsLoadingStateFlow.collect{
                Log.d("TAGGG","Loading = $it")
            }
        }

        lifecycleScope.launch {
            viewModel.patientsErrorStateFlow.collect{
                Log.d("TAGGG",it.toString())

            }
        }
    }
}