package com.example.patientapp.domain.model.delete

import com.google.gson.annotations.SerializedName

data class PatientDeleteResponse(
    @SerializedName("status")
    val status:Int,
    @SerializedName("message")
    val message: String,
)
