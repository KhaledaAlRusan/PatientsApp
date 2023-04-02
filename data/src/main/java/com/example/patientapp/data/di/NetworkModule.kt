package com.example.patientapp.data.di

import com.example.patientapp.data.datasource.PatientDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    val baseUrl = "https://patients-app-api.herokuapp.com"

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun providePatient(retrofit: Retrofit): PatientDataSource {
        return retrofit.create(PatientDataSource::class.java)
    }

//    @Provides
//    @Singleton
//    fun providePatientRepo(patientDataSource: PatientDataSource):PatientsRepo{
//        return PatientsRepo(patientDataSource)
//    }


}