package com.example.patientapp.domain.model.details

import com.example.patientapp.domain.model.patients.Data

data class GetPatientModel(
    val status:Int,
    val message:String,
    val data:Data
)
