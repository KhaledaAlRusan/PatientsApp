package com.example.patientapp.datasource

import com.example.patientapp.model.Patients
import retrofit2.http.GET

interface PatientDataSource {

    @GET("/patients")
    suspend fun getPatient(): Patients
}