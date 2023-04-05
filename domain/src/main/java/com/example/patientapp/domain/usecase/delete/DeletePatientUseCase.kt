package com.example.patientapp.domain.usecase.delete

import com.example.patientapp.domain.model.delete.DeletePatientResponseModel
import com.example.patientapp.domain.repo.PatientsRepo
import javax.inject.Inject

class DeletePatientUseCase @Inject constructor(private val repo: PatientsRepo) {
    suspend operator fun invoke(id: String?): DeletePatientResponseModel {
        return repo.deletePatient(id)
    }
}