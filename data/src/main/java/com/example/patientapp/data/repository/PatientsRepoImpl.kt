package com.example.patientapp.data.repository

import com.example.patientapp.data.datasource.PatientDataSource
import com.example.patientapp.domain.model.add.BodyAddPatientModel
import com.example.patientapp.domain.model.patients.Data
import com.example.patientapp.domain.model.add.AddPatientRemoteModel
import com.example.patientapp.domain.model.delete.DeletePatientResponseModel
import com.example.patientapp.domain.repo.PatientsRepo
import javax.inject.Inject

class PatientsRepoImpl @Inject constructor(private val patientDataSource: PatientDataSource):
    PatientsRepo {

    override suspend fun getPatients(): List<Data?>? {
        val listSorted = patientDataSource.getPatients().data?.sortedBy { it?.name }
        return listSorted
    }

    override suspend fun addPatient(body: BodyAddPatientModel): AddPatientRemoteModel {
        return patientDataSource.addPatient(body)
    }

    override suspend fun deletePatient(id: String?): DeletePatientResponseModel {
        return patientDataSource.deletePatient(id)
    }

    override suspend fun getPatientById(id: String): Data {
        return patientDataSource.getPatientById(id).data
    }


}