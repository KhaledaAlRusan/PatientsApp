package com.example.patientapp.data.datasource

import com.example.patientapp.domain.model.BaseWrapper
import com.example.patientapp.domain.model.add.BodyAddPatientModel
import com.example.patientapp.domain.model.add.AddPatientRemoteModel
import com.example.patientapp.domain.model.delete.DeletePatientResponseModel
import com.example.patientapp.domain.model.patients.Data
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface PatientDataSource {

    @GET("/patients")
    suspend fun getPatients(): BaseWrapper<List<Data?>?>

    @POST("/patients")
    suspend fun addPatient(@Body bodyAddPatientModel: BodyAddPatientModel): AddPatientRemoteModel

    @DELETE("/patients/{id}")
    suspend fun deletePatient(@Path("id") id: String?): DeletePatientResponseModel

    @GET("/patients/{id}")
    suspend fun  getPatientById(@Path("id") id :String): BaseWrapper<Data>
}