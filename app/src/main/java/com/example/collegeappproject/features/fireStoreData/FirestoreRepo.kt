package com.example.collegeappproject.features.fireStoreData

import android.util.Log
import com.example.collegeappproject.models.NotesModel
import com.example.collegeappproject.models.NoticeModel
import com.example.collegeappproject.utils.ResultState
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObjects
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface FirestoreRepo
{
    fun getFirestoreData() : Flow<ResultState<List<NotesModel>>>

    fun getAssignmentData() : Flow<ResultState<List<NotesModel>>>


}