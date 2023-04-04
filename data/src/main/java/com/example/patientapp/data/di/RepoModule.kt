package com.example.patientapp.data.di

import com.example.patientapp.data.datasource.PatientDataSource
import com.example.patientapp.data.repository.PatientsRepoImpl
import com.example.patientapp.domain.repo.PatientsRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepoModule {

    @Provides
    fun provideRepoPatients(patientDataSource: PatientDataSource): PatientsRepo {
        return PatientsRepoImpl(patientDataSource)
    }
}