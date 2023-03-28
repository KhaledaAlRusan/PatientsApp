package com.example.patientapp.features.patients

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.patientapp.model.Data
import com.example.patientapp.repository.PatientsRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PatientsViewModel @Inject constructor(private val repo: PatientsRepo):ViewModel() {

    private val _patientsStateFlow:MutableStateFlow<List<Data?>?> = MutableStateFlow(emptyList())
    val patientsStateFlow:StateFlow<List<Data?>?> = _patientsStateFlow

    private val _patientsLoadingStateFlow:MutableStateFlow<Boolean> = MutableStateFlow(true)
    val patientsLoadingStateFlow:StateFlow<Boolean> = _patientsLoadingStateFlow

    private val _patientsErrorStateFlow:MutableStateFlow<Exception?> = MutableStateFlow(null)
    val patientsErrorStateFlow:StateFlow<Exception?> = _patientsErrorStateFlow

    init {
        getPatients()
    }


    fun getPatients(){
        viewModelScope.launch {

            try {
                _patientsStateFlow.emit(repo.getPatient())
            }
            catch (e:Exception){
                _patientsErrorStateFlow.emit(e)
            }
            _patientsLoadingStateFlow.emit(false)


        }
    }
}