package com.example.patientapp.domain.usecase.details

import com.example.patientapp.domain.model.patients.PatientResponse
import com.example.patientapp.domain.repo.PatientsRepo
import javax.inject.Inject

class GetPatientByIdUseCase @Inject constructor(private val repo: PatientsRepo) {

    suspend operator fun invoke(id:String): PatientResponse {
        return repo.getPatient(id)
    }
}