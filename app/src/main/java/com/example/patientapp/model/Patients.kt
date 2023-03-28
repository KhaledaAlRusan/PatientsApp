package com.example.patientapp.model


import com.google.gson.annotations.SerializedName

data class Patients(
    @SerializedName("data")
    val `data`: List<Data?>? = null,
    @SerializedName("message")
    val message: String? = null,
    @SerializedName("status")
    val status: Int? = null
)