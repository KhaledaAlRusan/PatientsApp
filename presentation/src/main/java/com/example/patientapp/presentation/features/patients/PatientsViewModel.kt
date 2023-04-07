package com.example.patientapp.presentation.features.patients

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.patientapp.domain.model.delete.DeletePatientResponseModel
import com.example.patientapp.domain.model.patients.Data
import com.example.patientapp.domain.usecase.delete.DeletePatientUseCase
import com.example.patientapp.domain.usecase.patients.GetPatientSortedByNameUseCase

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PatientsViewModel @Inject constructor(private val getPatientSortedByNameUseCase: GetPatientSortedByNameUseCase,
private val deletePatientUseCase: DeletePatientUseCase
):ViewModel() {

    private val _patientsStateFlow:MutableStateFlow<List<Data?>?> = MutableStateFlow(emptyList())
    val patientsStateFlow:StateFlow<List<Data?>?> = _patientsStateFlow


    private val _deletePatientsLiveData:MutableLiveData<DeletePatientResponseModel?> = MutableLiveData()
    val deletePatientsLiveData:LiveData<DeletePatientResponseModel?> = _deletePatientsLiveData

    private val _patientsLoadingStateFlow:MutableStateFlow<Boolean> = MutableStateFlow(false)
    val patientsLoadingStateFlow:StateFlow<Boolean> = _patientsLoadingStateFlow

    private val _patientsErrorStateFlow:MutableStateFlow<Exception?> = MutableStateFlow(null)
    val patientsErrorStateFlow:StateFlow<Exception?> = _patientsErrorStateFlow

    init {
        getPatients()
    }


     fun getPatients(){
        viewModelScope.launch {
            _patientsLoadingStateFlow.emit(true)
            try {
                _patientsStateFlow.emit(getPatientSortedByNameUseCase.invoke())
            }
            catch (e:Exception){
                _patientsErrorStateFlow.emit(e)
            }
            _patientsLoadingStateFlow.emit(false)

        }
    }

    fun deletePatients(id: String?){
        viewModelScope.launch {

            try {
                _deletePatientsLiveData.postValue(deletePatientUseCase.invoke(id))
            }
            catch (e:Exception){
                _patientsErrorStateFlow.emit(e)
            }
            _patientsLoadingStateFlow.emit(false)

        }
    }
}