package com.example.patientapp.data.repository

import com.example.patientapp.data.datasource.PatientDataSource
import com.example.patientapp.domain.model.add.BodyAddPatientModel
import com.example.patientapp.domain.model.patients.Data
import com.example.patientapp.domain.model.add.AddPatientRemoteModel
import com.example.patientapp.domain.repo.PatientsRepo
import javax.inject.Inject

class PatientsRepoImpl @Inject constructor(private val patientDataSource: PatientDataSource):
    PatientsRepo {

    override suspend fun getPatients(): List<Data?>? {
        val listSorted = patientDataSource.getPatients().data?.sortedBy { it?.name }
        return listSorted
    }

    override suspend fun addPatient(bodyAddPatientModel: BodyAddPatientModel): AddPatientRemoteModel {
        return patientDataSource.addPatient(bodyAddPatientModel)
    }
}