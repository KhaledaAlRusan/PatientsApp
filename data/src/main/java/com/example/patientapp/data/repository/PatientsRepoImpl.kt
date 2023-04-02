package com.example.patientapp.data.repository

import com.example.patientapp.data.datasource.PatientDataSource
import com.example.patientapp.domain.model.patients.Data
import com.example.patientapp.domain.repo.patients.PatientsRepo
import javax.inject.Inject

class PatientsRepoImpl @Inject constructor(private val patientDataSource: PatientDataSource):
    PatientsRepo {

    override suspend fun getPatients(): List<Data?>? {
        val listSorted = patientDataSource.getPatient().data?.sortedBy { it?.name }
        return listSorted
    }

}