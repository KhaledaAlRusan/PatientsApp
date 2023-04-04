package com.example.patientapp.domain.model.add

import com.example.patientapp.domain.model.patients.Test
import com.google.gson.annotations.SerializedName

data class AddPatientRemoteModel(
    val address: String? = null,
    val birthdate: String? = null,
    val condition: String? = null,
    val createdAt: String? = null,
    val email: String? = null,
    val gender: String? = null,
    val id: String? = null,
    val mobile: String? = null,
    val name: String? = null,
    val photo: String? = null,
    val tests: List<Test?>? = null,
    val updatedAt: String? = null,
    val v: Int? = null,
)
