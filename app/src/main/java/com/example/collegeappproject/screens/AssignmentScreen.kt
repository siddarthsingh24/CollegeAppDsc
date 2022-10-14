package com.example.collegeappproject.screens

import android.content.Context
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import cafe.adriel.voyager.core.screen.Screen
import com.example.collegeappproject.features.fireStoreData.FireStoreViewModel
import com.example.collegeappproject.utils.UtilsFunctions

object AssignmentScreen :Screen {

    @Composable
    override fun Content() {
        val fireStoreViewModel: FireStoreViewModel = hiltViewModel()
        val response = fireStoreViewModel.assignmentResponse.value

        LazyColumn() {
            items(response.data,
                key = { it.key!! }
            ) { items ->
                UtilsFunctions.EachRow2(notesModel = items)
            }


        }
    }
}