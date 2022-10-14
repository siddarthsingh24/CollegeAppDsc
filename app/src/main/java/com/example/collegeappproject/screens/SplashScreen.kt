package com.example.collegeappproject.screens

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.collegeappproject.ui.theme.CollegeAppProjectTheme
import com.example.collegeappproject.utils.UtilsFunctions
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        UtilsFunctions.actionBarRemove(window)
        setContent {
            CollegeAppProjectTheme {
                UtilsFunctions.LottieAnimationTheme()

            }
        }
        GlobalScope.launch {
            try {
                delay(4000L)
                startActivity(Intent(this@MainActivity, OnBoardingScreen::class.java))
                finish()
            } catch (e: Exception) {
                Log.d("kotlin", "onCreate: ${e.toString()}")
            }
        }
    }
}

