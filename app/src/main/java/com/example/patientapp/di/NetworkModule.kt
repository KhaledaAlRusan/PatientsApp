package com.example.patientapp.di

import com.example.patientapp.datasource.PatientDataSource
import com.example.patientapp.repository.PatientsRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
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
    fun providePatient(retrofit: Retrofit):PatientDataSource{
        return retrofit.create(PatientDataSource::class.java)
    }

//    @Provides
//    @Singleton
//    fun providePatientRepo(patientDataSource: PatientDataSource):PatientsRepo{
//        return PatientsRepo(patientDataSource)
//    }


}