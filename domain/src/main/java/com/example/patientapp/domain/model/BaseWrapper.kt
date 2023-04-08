package com.example.patientapp.domain.model

data class BaseWrapper<T>(
    val status:Int,
    val message:String,
    val data:T
)
