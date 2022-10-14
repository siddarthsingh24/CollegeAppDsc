package com.example.collegeappproject.screens

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import com.example.collegeappproject.utils.UtilsFunctions

data class WebViewScreen(val url:String) :Screen {
    @Composable
    override fun Content() {
        UtilsFunctions.WebViewUrl(url = url)
    }
}

data class WebViewScreenNotes(var url :String) :Screen {
    @Composable
    override fun Content() {
       UtilsFunctions.WebViewUrl2(url = url)
    }
}