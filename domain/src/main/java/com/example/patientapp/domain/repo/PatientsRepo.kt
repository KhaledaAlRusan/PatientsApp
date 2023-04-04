package com.example.patientapp.domain.repo

import com.example.patientapp.domain.model.add.AddPatientRemoteModel
import com.example.patientapp.domain.model.add.BodyAddPatientModel
import com.example.patientapp.domain.model.patients.Data

interface PatientsRepo {

    suspend fun getPatients(): List<Data?>?

    suspend fun addPatient(body: BodyAddPatientModel): AddPatientRemoteModel
}