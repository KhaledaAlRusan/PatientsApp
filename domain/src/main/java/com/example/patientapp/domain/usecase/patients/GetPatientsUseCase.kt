package com.example.patientapp.domain.usecase.patients

import com.example.patientapp.domain.model.patients.PatientResponse
import com.example.patientapp.domain.repo.PatientsRepo
import javax.inject.Inject

class GetPatientsUseCase @Inject constructor(private val repo: PatientsRepo) {

    suspend operator fun invoke(): List<PatientResponse?>?{
        return repo.getPatients()?.sortedBy { it?.name }
    }
}