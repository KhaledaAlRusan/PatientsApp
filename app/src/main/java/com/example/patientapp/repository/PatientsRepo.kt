package com.example.patientapp.repository

import com.example.patientapp.datasource.PatientDataSource
import com.example.patientapp.model.Data
import com.example.patientapp.model.Patients
import javax.inject.Inject

class PatientsRepo @Inject constructor(private val patientDataSource: PatientDataSource) {

    suspend fun getPatient(): List<Data?>? {
        return patientDataSource.getPatient().data
    }

}