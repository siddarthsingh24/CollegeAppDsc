package com.example.collegeappproject.features.firebaseAuth

import androidx.lifecycle.ViewModel
import com.example.collegeappproject.models.AuthUser
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val repo: AuthRepo) :ViewModel() {

    fun singUpUser(auth :AuthUser) =repo.createUser(auth)

    fun loginInUser(auth: AuthUser) = repo.signInUser(auth)

}