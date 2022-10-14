package com.example.collegeappproject.screens

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.ButtonDefaults.buttonColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import com.example.collegeappproject.R
import com.example.collegeappproject.ui.theme.CollegeAppProjectTheme
import com.example.collegeappproject.ui.theme.LightSkyBlue
import com.example.collegeappproject.ui.theme.SkyBlue
import com.example.collegeappproject.utils.UtilsFunctions



class LoginSignUpWelcome : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        UtilsFunctions.actionBarRemove(window)
        UtilsFunctions.currentUserCheck(this,this)
        setContent {
            CollegeAppProjectTheme {

                LoginSignUpWelcomeScreen(this)
            }
        }
    }
}

@Composable
fun LoginSignUpWelcomeScreen(context:Context){
    Scaffold(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(40.dp)
                .background(Color.White),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {

              Text(modifier = Modifier.fillMaxWidth(),text ="Welcome",
                  style = TextStyle(

                  fontSize = 30.sp,
                  fontWeight = FontWeight.Bold,
                  color = SkyBlue,


              ))

            Spacer(modifier = Modifier.height(10.dp))
            
            Text(text = "Please login and signup to continue using this app ",
                style = TextStyle(
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Light,
                    color = Color.LightGray

                ),
                modifier = Modifier.fillMaxWidth(0.9f)


                )



            Spacer(modifier = Modifier.height(20.dp))

             Image(painter = painterResource(id = R.drawable.undraw_professor),
                 contentDescription ="LoginAndSingUpImage",
                 modifier = Modifier
                     .fillMaxWidth()
                     .offset(x = -50.dp))


            Spacer(modifier = Modifier.height(5.dp))
            Text(text ="Enter via social networks ", modifier = Modifier.fillMaxWidth(),style = TextStyle(

                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = SkyBlue


            

                ) )


            Spacer(modifier = Modifier.height(20.dp))
            
            
            Row(horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()) {
                 ImageCircleVector(imageId = R.drawable.facebook_icon)
                
                Spacer(modifier = Modifier.padding(start=20.dp))


                ImageCircleVector(imageId = R.drawable.google_icon)


            }

            Spacer(modifier = Modifier.height(30.dp))

            Text(text ="or login with email", modifier = Modifier.fillMaxWidth()
                ,style = TextStyle(

                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center

            ) )
            Spacer(modifier = Modifier.height(30.dp))
            Button(
                onClick = { /*TODO*/ },colors = buttonColors(backgroundColor = LightSkyBlue),
                shape = RoundedCornerShape(10),
                modifier = Modifier
                    .fillMaxWidth()
                    .size(50.dp),
            ) {
                       Text(text = "Sign Up",style = TextStyle(
                           fontSize = 17.sp, color = Color.White,
                           fontWeight = FontWeight.Bold
                       )
                       )
            }


            Spacer(modifier = Modifier.height(30.dp))


            Text(buildAnnotatedString {

                    append("You already have an account ?  ")
                    withStyle(SpanStyle(color = SkyBlue)) {
                        append ("Login")
                    }




            }, modifier = Modifier
                .clickable {
                    try {
                        val bundle1 = Bundle()
                        startActivity(context, Intent(context, LoginScreenTwo::class.java), bundle1)
                    } catch (e: Exception) {
                        Log.d("error", "${e.toString()}")
                    }
                }
                .fillMaxWidth(), style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp,
                textAlign = TextAlign.Center

            ))




        }

    }
}


@Composable
fun ImageCircleVector(imageId :Int){

    Box(
        Modifier
            .shadow(
                elevation = 10.dp,
                shape = CircleShape, clip = true
            )
            .size(70.dp)
            .background(Color.White)) {
        Image(
            painter = painterResource(imageId),
            contentDescription = "imageContent",
//            contentScale = ContentScale.Crop, // crop the image

            modifier = Modifier
                .size(20.dp)
                .shadow(shape = CircleShape, elevation = 0.dp)
                .align(Center)        // clip to the circle shape
        )
    }
}

