package com.example.collegeappproject.features.firebaseAuth

import android.util.Log
import com.example.collegeappproject.models.AuthUser
import com.example.collegeappproject.utils.ResultState
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class AuthRepoImplementation @Inject constructor(private val authdb:FirebaseAuth) : AuthRepo {
    override fun createUser(auth: AuthUser): Flow<ResultState<String>> =callbackFlow{
        trySend(ResultState.Loading)

        authdb.createUserWithEmailAndPassword(
            auth.email!!,
            auth.password!!
        ).addOnCompleteListener {
            if(it.isSuccessful){
                trySend(ResultState.Success("User created successfully"))
                Log.d("main", "current user id: ${authdb.currentUser?.uid}")
            }
        }.addOnFailureListener {
            trySend(ResultState.Failure(it))
        }

        awaitClose {
            close()
        }
    }

    override fun signInUser(auth: AuthUser): Flow<ResultState<String>> =callbackFlow{
        trySend(ResultState.Loading)

        authdb.signInWithEmailAndPassword(
            auth.email!!,
            auth.password!!
        ).addOnSuccessListener {
            trySend(ResultState.Success("login Successfully"))
            Log.d("main", "current user id: ${authdb.currentUser?.uid}")
        }.addOnFailureListener {
            trySend(ResultState.Failure(it))
        }
        awaitClose {
            close()
        }
    }

}