package com.example.patientapp.presentation.features

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.patientapp.presentation.R
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}