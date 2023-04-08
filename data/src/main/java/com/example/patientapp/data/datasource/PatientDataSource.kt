package com.example.patientapp.data.datasource

import com.example.patientapp.domain.model.BaseWrapper
import com.example.patientapp.domain.model.add.AddPatientRequest
import com.example.patientapp.domain.model.add.AddPatientResponse
import com.example.patientapp.domain.model.delete.PatientDeleteResponse
import com.example.patientapp.domain.model.patients.PatientResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface PatientDataSource {

    @GET("/patients")
    suspend fun getPatients(): BaseWrapper<List<PatientResponse>?>

    @POST("/patients")
    suspend fun addPatient(@Body bodyAddPatientModel: AddPatientRequest): AddPatientResponse

    @DELETE("/patients/{id}")
    suspend fun deletePatient(@Path("id") id: String?): PatientDeleteResponse

    @GET("/patients/{id}")
    suspend fun  getPatient(@Path("id") id :String):BaseWrapper<PatientResponse>
}