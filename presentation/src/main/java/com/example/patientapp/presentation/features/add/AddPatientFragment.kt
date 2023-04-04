package com.example.patientapp.presentation.features.add

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isEmpty
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.patientapp.domain.model.add.BodyAddPatientModel
import com.example.patientapp.presentation.databinding.FragmentAddPatientBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AddPatientFragment:Fragment(){
    private lateinit var binding: FragmentAddPatientBinding
    private val viewModel:AddPatientViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("TAGGG","Enterd the fragment")
        binding = FragmentAddPatientBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        initListener()
        initObserver()
    }

    private fun initListener() {
        binding.btnAdd.setOnClickListener{
            if(infoIsValid()){
                val body = getInfoPatient()
                viewModel.addPatient(body)
            }
        }
    }

    private fun getInfoPatient(): BodyAddPatientModel {
        return BodyAddPatientModel(
            name = binding.editTextFullName.text.toString(),
            address = binding.editTextAddress.text.toString(),
            gender = binding.editTextGender.text.toString(),
            birthdate = binding.editTextBirthdate.text.toString(),
            mobile = binding.editTextMobile.text.toString(),
            email = binding.editTextEmail.text.toString(),
        )
    }

    private fun infoIsValid(): Boolean {
        var isValid = true

        if(binding.editTextAddress.text?.isEmpty() == true){
            isValid = false
            binding.textFullName.error = "Field is Empty"
        }
        if(binding.editTextEmail.text?.isEmpty()== true){
            isValid = false
            binding.textEmail.error = "Field is Empty"
        }
        if(binding.editTextAddress.text?.isEmpty()== true){
            isValid = false
            binding.textAddress.error = "Field is Empty"
        }
        if(binding.editTextBirthdate.text?.isEmpty()== true){
            isValid = false
            binding.textBirthdate.error = "Field is Empty"
        }
        if(binding.editTextGender.text?.isEmpty()== true){
            isValid = false
            binding.textGender.error = "Field is Empty"
        }
        if(binding.editTextMobile.text?.isEmpty()== true){
            isValid = false
            binding.textMobile.error = "Field is Empty"
        }
        return isValid
    }

    private fun initObserver() {
        lifecycleScope.launch {
            viewModel.addPatientStateFlow.collect{
                if(it != null){
                    Toast.makeText(requireContext(),"Patient added successfully",Toast.LENGTH_SHORT).show()
                }
            }
        }

        lifecycleScope.launch {
            viewModel.addPatientLoadingStateFlow.collect{
                binding.progressCircular.isVisible = it
            }
        }


        lifecycleScope.launch {
            viewModel.addPatientErrorStateFlow.collect{
                if(it !=null){
                    Log.d("TAGGG",it.toString())
                }
            }
        }
    }
}