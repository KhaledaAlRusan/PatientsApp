package com.example.patientapp.data.repository

import com.example.patientapp.data.datasource.PatientDataSource
import com.example.patientapp.domain.model.add.AddPatientRequest
import com.example.patientapp.domain.model.patients.PatientResponse
import com.example.patientapp.domain.model.add.AddPatientResponse
import com.example.patientapp.domain.model.delete.PatientDeleteResponse
import com.example.patientapp.domain.repo.PatientsRepo
import javax.inject.Inject

class PatientsRepoImpl @Inject constructor(private val patientDataSource: PatientDataSource):
    PatientsRepo {

    override suspend fun getPatients(): List<PatientResponse>? {
        val listSorted = patientDataSource.getPatients().data?.sortedBy { it.name }
        return listSorted
    }

    override suspend fun addPatient(body: AddPatientRequest): AddPatientResponse {
        return patientDataSource.addPatient(body)
    }

    override suspend fun deletePatient(id: String?): PatientDeleteResponse {
        return patientDataSource.deletePatient(id)
    }

    override suspend fun getPatient(id: String): PatientResponse {
        return patientDataSource.getPatient(id).data
    }


}