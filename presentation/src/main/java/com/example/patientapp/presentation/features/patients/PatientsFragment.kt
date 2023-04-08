package com.example.patientapp.presentation.features.patients

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.patientapp.core.BaseFragment
import com.example.patientapp.domain.model.delete.DeletePatientResponseModel
import com.example.patientapp.presentation.R
import com.example.patientapp.presentation.databinding.PatientsFragmentBinding
import com.example.patientapp.presentation.features.patients.adapters.PatientsAdapter
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PatientsFragment : BaseFragment<PatientsFragmentBinding> (R.layout.patients_fragment) {

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
        adapter = PatientsAdapter(::deletePatient,::onClickItem)
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
        lifecycleScope.launch {
            viewModel.deletePatientsLiveData.observe(viewLifecycleOwner,::onPatientDeletedSuccess)
        }
    }


    private fun onPatientDeletedSuccess(responseModel: DeletePatientResponseModel?){
        if(responseModel !=null){
            Toast.makeText(requireContext(), responseModel.message,Toast.LENGTH_SHORT).show()
            viewModel.getPatients()
        }
    }



    private fun deletePatient(id:String?){
        MaterialAlertDialogBuilder(requireContext())
            .setMessage("Do you want to delete this item?")
            .setNegativeButton("No"){dialog,_ ->
                dialog.dismiss()
            }
            .setPositiveButton("Yes"){dialog,_ ->
                viewModel.deletePatients(id)
                dialog.dismiss()
            }
            .show()
    }

    private fun onClickItem(id:String?){
        findNavController().navigate(R.id.action_patientsFragment_to_detailsPatientFragment  , bundleOf("id" to id))
    }
}