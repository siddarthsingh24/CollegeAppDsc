package com.example.collegeappproject.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.collegeappproject.ui.theme.CollegeAppProjectTheme
import com.example.collegeappproject.utils.UtilsFunctions
import com.example.collegeappproject.utils.ViewPagerUi

class OnBoardingScreen : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        UtilsFunctions.actionBarRemove(window)
        setContent {
            CollegeAppProjectTheme {

                ViewPagerUi(this)

            }
        }
    }
}



