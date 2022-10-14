package com.example.collegeappproject.utils

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import com.example.collegeappproject.models.ViewPagerModel
import com.example.collegeappproject.screens.HomeScreen
import com.example.collegeappproject.screens.LoginSignUpWelcome
import com.example.collegeappproject.screens.OnBoardingScreen
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ViewPagerUi(context: Context){


    val pageState = rememberPagerState()

    Column(modifier = Modifier.background(Color.White)) {
        HorizontalPager(count = ViewPagerModel.data.size,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .weight(1f),
            state = pageState



        ) { currentPage->

            PageUi(viewPagerModel = ViewPagerModel.data[currentPage])





        }

        AnimatedVisibility(visible = pageState.currentPage == 2,
            modifier = Modifier.align(Alignment.CenterHorizontally)) {
            Button(
                onClick = {

                    try {
                        val bundle = Bundle()
                        startActivity(
                            context,
                            Intent(context, LoginSignUpWelcome::class.java),
                            bundle
                        )
                    } catch (e: Exception) {
                        Log.d("kotlin", "ViewPagerUi:${e.toString()} ")
                    }
                },
            ) {
                Text(text = "Getting Started")
            }
        }
        HorizontalPagerIndicator(
            pagerState = pageState,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(20.dp),
            activeColor = colorResource(id = com.example.collegeappproject.R.color.purple_500)
        )

    }


}

@Composable
fun PageUi(viewPagerModel: ViewPagerModel){


    Column(modifier = Modifier
        .fillMaxWidth()
        .background(color = Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painter = painterResource(id = viewPagerModel.image), contentDescription =viewPagerModel.des )
        Spacer(modifier = Modifier.height(30.dp))
        Text(text = viewPagerModel.des, style = TextStyle(
            fontSize = 30.sp,


        ))
        Spacer(modifier = Modifier.height(20.dp))

        Text(text = "consectetur adipiscing elit." +
                " Donec tempus nulla in massa dignissim, at lobortis mi pulvinar. Nunc " +
                "vitae ornare mi," +
                " et ultrices mauris." +
                " Fusce rhoncus bibendum pretium. Vestibulum.", style = TextStyle(
            fontSize = 20.sp, color = Color.LightGray) , textAlign = TextAlign.Center, modifier =
        Modifier.padding(30.dp)
        )



    }
}