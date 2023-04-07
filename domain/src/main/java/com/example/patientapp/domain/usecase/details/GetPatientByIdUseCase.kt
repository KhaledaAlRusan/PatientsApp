package com.example.patientapp.domain.usecase.details

import com.example.patientapp.domain.model.patients.Data
import com.example.patientapp.domain.repo.PatientsRepo
import javax.inject.Inject

class GetPatientByIdUseCase @Inject constructor(private val repo: PatientsRepo) {

    suspend operator fun invoke(id:String): Data {
        return repo.getPatientById(id)
    }
}