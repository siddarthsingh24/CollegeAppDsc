package com.example.collegeappproject.features.firebaseAuth

import com.example.collegeappproject.models.AuthUser
import com.example.collegeappproject.utils.ResultState
import kotlinx.coroutines.flow.Flow


interface AuthRepo {

    fun createUser(auth :AuthUser) : Flow<ResultState<String>>

    fun signInUser(auth: AuthUser) : Flow<ResultState<String>>

}