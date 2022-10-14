package com.example.collegeappproject.screens

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import cafe.adriel.voyager.core.screen.Screen
import com.example.collegeappproject.features.webScrapping.WebScrapingViewModel
import com.example.collegeappproject.utils.ResultState
import com.example.collegeappproject.utils.UtilsFunctions.EachRow



class NoticeScreen(context: Context) :Screen {
    val context = context
    @Composable
    override fun Content() {

       val webScrapingViewModel: WebScrapingViewModel = hiltViewModel()

            Scaffold(


            ) {
                when (val response = webScrapingViewModel.news_response.value) {

                    is ResultState.Failure -> {
                        Text(text = " ${response.msg}")
                    }
                    is ResultState.Loading -> {

                        Box (modifier=Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center){
                            CircularProgressIndicator()
                        }

                    }

                    is ResultState.Success -> {

                        LazyColumn() {
                            items(response.data) { it ->
                                EachRow(noticeModel = it,context)
                            }
                        }
                    }
                }
            }



        }

    }



