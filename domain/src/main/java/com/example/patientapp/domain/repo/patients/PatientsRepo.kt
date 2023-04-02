package com.example.patientapp.domain.repo.patients

import com.example.patientapp.domain.model.patients.Data

interface PatientsRepo {
    suspend fun getPatients():List<Data?>?
}