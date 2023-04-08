package com.example.patientapp.presentation.features.details

import androidx.lifecycle.*
import com.example.patientapp.domain.model.patients.PatientResponse
import com.example.patientapp.domain.usecase.details.GetPatientByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val getPatientByIdUseCase: GetPatientByIdUseCase,
    state:SavedStateHandle
):ViewModel() {

    private val _detailsStateFlow: MutableStateFlow<PatientResponse?> = MutableStateFlow(null)
    val detailsStateFlow: StateFlow<PatientResponse?> = _detailsStateFlow


    private val _detailsLoadingStateFlow: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val detailsLoadingStateFlow: StateFlow<Boolean> = _detailsLoadingStateFlow

    private val _detailsErrorStateFlow: MutableStateFlow<Exception?> = MutableStateFlow(null)
    val detailsErrorStateFlow: StateFlow<Exception?> = _detailsErrorStateFlow

    private val savedStateHandle = state

    init {
        details()
    }


    fun details(){
        val id  = savedStateHandle.get<String>("id")?:"-1"


        viewModelScope.launch {

            _detailsLoadingStateFlow.emit(true)
            try {
                _detailsStateFlow.emit(getPatientByIdUseCase.invoke(id))
            }
            catch (e:Exception){
                _detailsErrorStateFlow.emit(e)
            }
            _detailsLoadingStateFlow.emit(false)

        }
    }
}