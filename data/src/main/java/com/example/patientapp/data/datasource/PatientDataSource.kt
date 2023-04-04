package com.example.patientapp.data.datasource

import com.example.patientapp.domain.model.add.BodyAddPatientModel
import com.example.patientapp.domain.model.patients.Patients
import com.example.patientapp.domain.model.add.AddPatientRemoteModel
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface PatientDataSource {

    @GET("/patients")
    suspend fun getPatients(): Patients

    @POST("/patients")
    suspend fun addPatient(@Body bodyAddPatientModel: BodyAddPatientModel): AddPatientRemoteModel
}