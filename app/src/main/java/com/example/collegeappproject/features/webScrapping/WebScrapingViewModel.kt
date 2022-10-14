package com.example.collegeappproject.features.webScrapping

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.collegeappproject.models.NoticeModel
import com.example.collegeappproject.utils.ResultState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject



@HiltViewModel
class WebScrapingViewModel
@Inject
constructor(private val webScrappingRepo: WebScrappingRepo) : ViewModel() {


    val response :MutableState<ResultState<List<NoticeModel>>> =
        mutableStateOf(ResultState.Empty)

    val news_response :MutableState<ResultState<List<NoticeModel>>> =
        mutableStateOf(ResultState.Empty)


    init {
        getPost()
        newsPost()
    }



    fun getPost() = viewModelScope.launch {
         webScrappingRepo.getPost()
             .onStart {

                 response.value = ResultState.Loading

              }.catch {
                 response.value = ResultState.Failure(it)

              }.collect{

                 response.value = ResultState.Success(it)
                 Log.d("meriList", "${it.toString()}")
              }
    }

    fun newsPost() = viewModelScope.launch {
        webScrappingRepo.getNewPost()
            .onStart {

                news_response.value = ResultState.Loading

            }.catch {
                news_response.value = ResultState.Failure(it)

            }.collect{

                news_response.value = ResultState.Success(it)
                Log.d("meriList", "${it.toString()}")
            }
    }



}