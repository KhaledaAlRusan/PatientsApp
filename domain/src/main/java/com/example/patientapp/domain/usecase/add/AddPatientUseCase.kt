package com.example.patientapp.domain.usecase.add

import com.example.patientapp.domain.model.add.AddPatientRequest
import com.example.patientapp.domain.model.add.AddPatientResponse
import com.example.patientapp.domain.repo.PatientsRepo
import javax.inject.Inject

class AddPatientUseCase @Inject constructor(private val repo: PatientsRepo) {
    suspend operator fun invoke(bodyAddPatientModel: AddPatientRequest): AddPatientResponse {
        return repo.addPatient(bodyAddPatientModel)
    }
}