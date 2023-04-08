package com.example.patientapp.domain.repo

import com.example.patientapp.domain.model.add.AddPatientRequest
import com.example.patientapp.domain.model.add.AddPatientResponse
import com.example.patientapp.domain.model.delete.PatientDeleteResponse
import com.example.patientapp.domain.model.patients.PatientResponse

interface PatientsRepo {

    suspend fun getPatients(): List<PatientResponse?>?

    suspend fun addPatient(body: AddPatientRequest): AddPatientResponse

    suspend fun deletePatient(id: String?): PatientDeleteResponse

    suspend fun getPatient(id:String):PatientResponse
}