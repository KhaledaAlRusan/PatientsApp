package com.example.patientapp.domain.usecase.patients

import com.example.patientapp.domain.model.add.BodyAddPatientModel
import com.example.patientapp.domain.model.add.AddPatientRemoteModel
import com.example.patientapp.domain.repo.PatientsRepo
import javax.inject.Inject

class AddPatientUseCase @Inject constructor(private val repo: PatientsRepo) {
    suspend operator fun invoke(bodyAddPatientModel: BodyAddPatientModel): AddPatientRemoteModel {
        return repo.addPatient(bodyAddPatientModel)
    }
}