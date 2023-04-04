package com.example.patientapp.presentation.features.patients

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.patientapp.domain.model.patients.Data
import com.example.patientapp.domain.repo.PatientsRepo

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PatientsViewModel @Inject constructor(private val repo: PatientsRepo):ViewModel() {

    private val _patientsStateFlow:MutableStateFlow<List<Data?>?> = MutableStateFlow(emptyList())
    val patientsStateFlow:StateFlow<List<Data?>?> = _patientsStateFlow

    private val _patientsLoadingStateFlow:MutableStateFlow<Boolean> = MutableStateFlow(false)
    val patientsLoadingStateFlow:StateFlow<Boolean> = _patientsLoadingStateFlow

    private val _patientsErrorStateFlow:MutableStateFlow<Exception?> = MutableStateFlow(null)
    val patientsErrorStateFlow:StateFlow<Exception?> = _patientsErrorStateFlow

    init {
        getPatients()
    }


    private fun getPatients(){
        viewModelScope.launch {

            try {
                _patientsStateFlow.emit(repo.getPatients())
            }
            catch (e:Exception){
                _patientsErrorStateFlow.emit(e)
            }
            _patientsLoadingStateFlow.emit(false)


        }
    }
}