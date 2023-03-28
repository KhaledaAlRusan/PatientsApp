package com.example.patientapp.model


import com.google.gson.annotations.SerializedName

data class Test(
    @SerializedName("date")
    val date: String? = null,
    @SerializedName("_id")
    val id: String? = null,
    @SerializedName("reading")
    val reading: String? = null,
    @SerializedName("type")
    val type: String? = null
)