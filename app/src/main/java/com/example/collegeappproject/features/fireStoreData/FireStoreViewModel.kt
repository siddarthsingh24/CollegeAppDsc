package com.example.collegeappproject.features.fireStoreData

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.collegeappproject.models.NotesModel
import com.example.collegeappproject.models.NoticeModel
import com.example.collegeappproject.utils.ResultState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FireStoreViewModel
@Inject
constructor(private val firestoreRepo: FirestoreRepo) :ViewModel() {


     private val _res:MutableState<FirestoreState> = mutableStateOf(FirestoreState())
     private val _assignmentRes :MutableState<FirestoreState> = mutableStateOf(FirestoreState())
     val res : State<FirestoreState> = _res
     val assignmentResponse = _assignmentRes

    init {
        getFireStoreData()
        getAssignmentData()
    }


    fun getFireStoreData() =viewModelScope.launch {

        firestoreRepo.getFirestoreData().

             collect{
                 when(it){
                    is ResultState.Empty -> {}
                     is ResultState.Failure ->{
                         _res.value = FirestoreState(error = it.msg.toString())
                     }
                   is  ResultState.Loading -> {
                       _res.value = FirestoreState(IsLoading = true)
                   }
                     is ResultState.Success -> {
                         _res.value = FirestoreState(data =it.data)
                     }
                 }
             }


    }

    fun getAssignmentData() = viewModelScope.launch {

        firestoreRepo.getAssignmentData().

        collect{
            when(it){
                is ResultState.Empty -> {}
                is ResultState.Failure ->{
                    _assignmentRes.value = FirestoreState(error = it.msg.toString())
                }
                is  ResultState.Loading -> {
                    _assignmentRes.value = FirestoreState(IsLoading = true)
                }
                is ResultState.Success -> {
                    _assignmentRes.value = FirestoreState(data =it.data)
                }
            }
        }


    }



}

data class FirestoreState(

    val data : List<NotesModel> =emptyList(),
    val error :String = "",
    val IsLoading :Boolean = false
)