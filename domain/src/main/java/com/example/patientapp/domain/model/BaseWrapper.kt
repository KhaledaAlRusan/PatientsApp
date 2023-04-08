package com.example.patientapp.domain.model

import com.google.gson.annotations.SerializedName

class BaseWrapper<T> (
    val message: String? = null,
    val status: Int? = null,
    val data:T
)