package com.example.patientapp.data.datasource

import com.example.patientapp.domain.model.patients.Patients
import retrofit2.http.GET

interface PatientDataSource {

    @GET("/patients")
    suspend fun getPatient(): Patients
}