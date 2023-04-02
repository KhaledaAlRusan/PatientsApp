package com.example.patientapp.domain.model.patients


import com.google.gson.annotations.SerializedName

data class Patients(
    @SerializedName("data")
    val `data`: List<Data?>? = null,
    @SerializedName("message")
    val message: String? = null,
    @SerializedName("status")
    val status: Int? = null
)