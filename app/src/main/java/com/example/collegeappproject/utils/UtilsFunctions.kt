package com.example.collegeappproject.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat.startActivity
import androidx.core.view.isVisible
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.airbnb.lottie.compose.*
import com.example.collegeappproject.R
import com.example.collegeappproject.models.NotesModel
import com.example.collegeappproject.models.NoticeModel
import com.example.collegeappproject.screens.HomeScreen
import com.example.collegeappproject.screens.WebViewScreen
import com.example.collegeappproject.screens.WebViewScreenNotes
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


object UtilsFunctions {


    @Composable
    fun WebViewUrl(url :String){




        AndroidView(factory = {

            WebView(it).apply {

                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
                webViewClient = WebViewClient()
                loadUrl("https://drive.google.com/viewerng/viewer?embedded=true&url=${url}")


            }
        }, update = {

            it.loadUrl("https://drive.google.com/viewerng/viewer?embedded=true&url=${url}")
        })
    }

    @Composable
    fun WebViewUrl2(url :String){




        AndroidView(factory = {

            WebView(it).apply {

                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
                webViewClient = WebViewClient()
                loadUrl(url)


            }
        }, update = {

            it.loadUrl(url)
        })
    }


    fun currentUserCheck(context: Context,activity: Activity){
        if (Firebase.auth.currentUser != null)
        {
            val i =Intent(context, HomeScreen::class.java)
            context.startActivity(i)


        }
    }
    fun actionBarRemove(window : Window){
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }


    @Composable
    fun EachRow(noticeModel: NoticeModel,context: Context) {

      val navigator = LocalNavigator.currentOrThrow

        Spacer(modifier = Modifier.height(10.dp))
        Row(modifier = Modifier
            .clickable {

                navigator.push(WebViewScreen(noticeModel.noticeLink))

            }
            .padding(4.dp)
            .background(Color.White)
            .fillMaxWidth()
            .border(width = 1.dp, shape = RoundedCornerShape(10), color = Color.LightGray)
            ,
            verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = R.drawable.undraw_newspaper_icon),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .border(1.dp, Color.Gray, shape = RoundedCornerShape(10))
                    .size(84.dp)
                    .background(Color.White)
                    .padding(20.dp)

            )



            Text(noticeModel.noticeText,
                modifier = Modifier
                    .background(
                        Color.White

                    )
                    .padding(start = 10.dp))

        }

    }


    @Composable
    fun LottieAnimationTheme(){



        val compositionResult: LottieCompositionResult =
            rememberLottieComposition(spec= LottieCompositionSpec.RawRes(R.raw.splashscreen_animation))

        val progress by animateLottieCompositionAsState(
            compositionResult.value,
            isPlaying = true,
            iterations = LottieConstants.IterateForever,
            speed = 1.0f
        )

        LottieAnimation(compositionResult.value, progress, modifier = Modifier.padding(80.dp) )
    }



    @Composable
    fun transparentActionBar() {


        val systemUiController = rememberSystemUiController()
        val useDarkIcons = !isSystemInDarkTheme()


        DisposableEffect(systemUiController, useDarkIcons) {
            // Update all of the system bar colors to be transparent, and use
            // dark icons if we're in light theme
            systemUiController.setSystemBarsColor(
                color = Color.Transparent,
                darkIcons = useDarkIcons
            )

            // setStatusBarColor() and setNavigationBarColor() also exist

            onDispose {}
        }

    }


    @Composable
    fun EachRow2(notesModel: NotesModel) {

        val navigator = LocalNavigator.currentOrThrow

        Spacer(modifier = Modifier.height(10.dp))
        Row(modifier = Modifier
            .clickable {

                navigator.push(WebViewScreenNotes(notesModel.item?.link.toString()))



            }
            .padding(4.dp)
            .background(Color.White)
            .fillMaxWidth()
            .border(width = 1.dp, shape = RoundedCornerShape(10), color = Color.LightGray)
            ,
            verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = R.drawable.undraw_newspaper_icon),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .border(1.dp, Color.Gray, shape = RoundedCornerShape(10))
                    .size(84.dp)
                    .background(Color.White)
                    .padding(20.dp)

            )



            Text(notesModel.item?.name!!,
                modifier = Modifier
                    .background(
                        Color.White

                    )
                    .padding(start = 10.dp))

        }

    }




}

fun GetUrlFromIntent(view: View?,context :Context,url :String) {

    val i = Intent(Intent.ACTION_VIEW)
    i.data = Uri.parse(url)
    context.startActivity(i)
}
