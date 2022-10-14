package com.example.collegeappproject.screens

import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.collegeappproject.features.fireStoreData.FireStoreViewModel
import com.example.collegeappproject.features.webScrapping.WebScrapingViewModel
import com.example.collegeappproject.utils.ResultState
import com.example.collegeappproject.utils.UtilsFunctions
import cafe.adriel.voyager.core.screen.Screen as Screen

object NotesScreen  : Screen {



    @Composable
    override fun Content() {
        val fireStoreViewModel: FireStoreViewModel = hiltViewModel()
        val response = fireStoreViewModel.res.value

        LazyColumn() {
            items(response.data,
                key = { it.key!! }
            ) { items ->
                UtilsFunctions.EachRow2(notesModel = items)
            }


        }
    }

}