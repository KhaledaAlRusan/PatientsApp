package com.example.patientapp.domain.patients

import com.example.patientapp.domain.model.patients.Data
import com.example.patientapp.domain.repo.patients.PatientsRepo
import javax.inject.Inject

class GetPatientsUseCase @Inject constructor(private val repo: PatientsRepo) {

    suspend operator fun invoke(): List<Data?>?{
        return repo.getPatients()?.sortedBy { it?.name }
    }
}