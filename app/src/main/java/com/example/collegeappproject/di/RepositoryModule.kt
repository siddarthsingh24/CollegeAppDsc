package com.example.collegeappproject.di

import com.example.collegeappproject.features.fireStoreData.FirestoreRepo
import com.example.collegeappproject.features.fireStoreData.FirestoreRepoImplement
import com.example.collegeappproject.features.firebaseAuth.AuthRepo
import com.example.collegeappproject.features.firebaseAuth.AuthRepoImplementation
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent


@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideFirebaseAuthRepo(repo : AuthRepoImplementation) : AuthRepo

    @Binds
    abstract fun provideFirestore(repo :FirestoreRepoImplement) : FirestoreRepo
}