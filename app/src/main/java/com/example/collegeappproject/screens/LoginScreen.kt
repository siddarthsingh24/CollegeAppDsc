package com.example.collegeappproject.screens

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.drawable.Icon
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.hilt.navigation.compose.hiltViewModel

import com.example.collegeappproject.R
import com.example.collegeappproject.features.firebaseAuth.AuthViewModel
import com.example.collegeappproject.features.webScrapping.WebScrapingViewModel
import com.example.collegeappproject.models.AuthUser
import com.example.collegeappproject.ui.theme.CollegeAppProjectTheme
import com.example.collegeappproject.ui.theme.LightSkyBlue
import com.example.collegeappproject.ui.theme.SkyBlue
import com.example.collegeappproject.utils.ResultState
import com.example.collegeappproject.utils.UtilsFunctions
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginScreen : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        UtilsFunctions.actionBarRemove(window)

        UtilsFunctions.currentUserCheck(this,this)


        setContent {
            CollegeAppProjectTheme {

                LoginScreenFun(this)
            }
        }
    }
}

@Composable
fun LoginScreenFun(context: Context,authViewModel: AuthViewModel= hiltViewModel()){
    Scaffold(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(40.dp)
                .background(Color.White),
            verticalArrangement = Arrangement.SpaceEvenly
//            verticalArrangement = Arrangement.SpaceEvenly
        ){

            Text(modifier = Modifier.fillMaxWidth(),text ="Sign Up",
                style = TextStyle(

                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    color = SkyBlue,


                    )
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(text = "Please Register with email and signup to continue using our app ",
                style = TextStyle(
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Light,
                    color = Color.LightGray

                ),
                modifier = Modifier.fillMaxWidth(0.9f)


            )


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

            Column(modifier = Modifier.fillMaxWidth(),
                 verticalArrangement = Arrangement.SpaceEvenly
                ) {
                var email by remember{
                    mutableStateOf("")
                }

                var password by remember{
                    mutableStateOf("")
                }

                var scope = rememberCoroutineScope()


                var isDialog by remember {
                    mutableStateOf(false)
                }

                if(isDialog){
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
                }

                TextField(
                    value = email,
                    onValueChange = {
                        email = it
                    },
                    label = { Text(text = "Email") },
                    modifier = Modifier
                        .background(Color.White)
                        .shadow(10.dp, shape = RoundedCornerShape(10))
                        .fillMaxWidth(),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.White,
                        focusedIndicatorColor = Color.White,
                        unfocusedIndicatorColor = Color.White

                    ),


                    )
                Spacer(modifier = Modifier.height(30.dp))
                TextField(
                    value = password,
                    onValueChange = {
                        password = it
                    },
                    label = { Text(text = "Password") },
                    modifier = Modifier
                        .background(Color.White)
                        .shadow(10.dp, shape = RoundedCornerShape(10))
                        .fillMaxWidth(),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.White,
                        focusedIndicatorColor = Color.White,
                        unfocusedIndicatorColor = Color.White

                    ),


                    )
                Spacer(modifier = Modifier.height(30.dp))
                TextInputField("Password")

                Spacer(modifier = Modifier.height(30.dp))

                Button(
                    onClick = {

                              scope.launch(Dispatchers.Main) {

                                  authViewModel.singUpUser(AuthUser(

                                      email, password
                                  )
                                  ).collect{

                                    isDialog=  when(it){

                                         is ResultState.Success->{

                                             context.startActivity(Intent(context, HomeScreen::class.java))


                                              false
                                         }
                                          is ResultState.Loading->{
                                             true
                                          }
                                          is ResultState.Failure->{

                                              false
                                          }
                                         is ResultState.Empty->{
                                             false
                                         }



                                    }



                                  }


                              }
                    },colors = ButtonDefaults.buttonColors(backgroundColor = LightSkyBlue),
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
            }
            

            
            Spacer(modifier = Modifier.height(20.dp))

            Text(buildAnnotatedString {

                append("You already have an account ?  ")
                withStyle(SpanStyle(color = SkyBlue)) {
                    append ("Login")
                }




            }, modifier = Modifier.clickable {
                val bundle3 = Bundle()
                try {
                    startActivity(
                        context,
                        Intent(context, LoginScreenTwo::class.java),
                        bundle3
                    )
                } catch (e: Exception) {
                    Log.d("error", "${e.toString()}")
                }
            })


        }
    }
}

@Composable
fun TextInputField(hint :String){

     var name by remember {
         mutableStateOf("")
     }


    TextField(
        value = name,
        onValueChange = {
            name = it
        },
        label = { Text(text = hint) },
        modifier = Modifier
            .background(Color.White)
            .shadow(10.dp, shape = RoundedCornerShape(10))
            .fillMaxWidth(),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.White,
            focusedIndicatorColor = Color.White,
            unfocusedIndicatorColor = Color.White

            ),


        )
}
