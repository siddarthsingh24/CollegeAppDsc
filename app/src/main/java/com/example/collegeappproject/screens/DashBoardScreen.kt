package com.example.collegeappproject.screens

import android.content.Context
import android.content.Intent
import android.graphics.Paint
import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.collegeappproject.R
import com.example.collegeappproject.models.NavigationItem
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch


class DashBoardScreen(context: Context) :Screen {

    var context= context

    @Composable
    override fun Content() {
     val navigator = LocalNavigator.currentOrThrow
        val scaffoldState = rememberScaffoldState()
        Scaffold(
            drawerContent = {
                            Column(modifier = Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally) {
                                Image(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .fillMaxSize(0.3f),
                                    painter = painterResource(
                                        id = R.drawable.undraw_portfolio_update_), contentDescription ="" )
                                Text("Maharaja Surajmal Institute is a private college located in Janakpuri, New Delhi, India.[1] The college is affiliated to Guru Gobind Singh Indraprastha University (GGSIPU) and s offering course BBA(G), BBA(B&I), BCA, BTECH, BCOM, B.Ed., running in morning and evening shifts."
                                  , modifier = Modifier.padding(5.dp), textAlign = TextAlign.Center,
                                    style = TextStyle(
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 15.sp
                                    )

                                )
                                Text(text = "Logout",
                                    textAlign = TextAlign.Center,
                                    style = TextStyle(
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 15.sp
                                    ),
                                    modifier = Modifier.clickable {
                                        try {

                                            Firebase.auth.signOut()
                                            navigator.pop()
                                            HomeScreen().finish()
                                        } catch (e: Exception) {
                                            Log.d("kotlin", "${e.toString()}")
                                        }

                                    })

                            }
                          
            },
            scaffoldState= scaffoldState,
            modifier = Modifier
                .background(Color.White)
                .fillMaxSize(),
            topBar = { TopAppBarCreated(scaffoldState)},
            bottomBar = {
                BottomNavigationBar()
            }
        ) {


            Column(modifier = Modifier.padding(20.dp),
                verticalArrangement = Arrangement.SpaceEvenly) {
                Spacer(modifier = Modifier.height(20.dp))
                SearchBar(hint = "Search anything you want")
                Spacer(modifier = Modifier.height(20.dp))

                Row(modifier= Modifier

                    .horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {

                    Spacer(modifier = Modifier.width(10.dp))
                    CardViewDashBoard(R.drawable.icons_ibooks,"Notes", NotesScreen)
                    Spacer(modifier = Modifier.width(20.dp))

                    CardViewDashBoard(R.drawable.icons_task,"Assignment",AssignmentScreen)
                    Spacer(modifier = Modifier.width(20.dp))

                    CardViewDashBoard(R.drawable.icons_news,"Syllabus",NotesScreen)
                    Spacer(modifier = Modifier.width(20.dp))









                }
                Spacer(modifier = Modifier.height(50.dp))
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp),
                    text = "Latest Updates", style = TextStyle(
                        fontWeight = FontWeight.Bold,

                        fontSize = 25.sp))

                Spacer(modifier = Modifier.height(30.dp))

                Row(modifier= Modifier

                    .horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {

                    Spacer(modifier = Modifier.width(10.dp))
                    CardViewDashBoardNews(
                        R.drawable.undraw_portfolio_update_,
                        "Get the Latest notice of msit in the pdf form",NewsScreen(context))
                    Spacer(modifier = Modifier.width(20.dp))

                    CardViewDashBoardNews(

                        R.drawable.undraw_design_community_rcft,"" +
                                "Get the latest News of msit  in the pdf form",NoticeScreen(context))
                    Spacer(modifier = Modifier.width(20.dp))

                    CardViewDashBoardNews(R.drawable.undraw_status_update,
                        "Get the latest notice of msit the the thee watch it in the pdf form",NoticeScreen(context))
                    Spacer(modifier = Modifier.width(20.dp))









                }







            }
        }


    }
}

@Composable
fun DashBoardScreen() {

}

@Composable
fun BottomNavigationBar() {
    val items = listOf(
        NavigationItem.Home,
        NavigationItem.Movies,
        NavigationItem.Books,
        NavigationItem.Profile
    )
    BottomNavigation(
        elevation = 0.dp,

        backgroundColor = colorResource(id = R.color.white),
        contentColor = Color.Black
    ) {
        items.forEach { item ->
            BottomNavigationItem(
                icon = { Icon(painterResource(id = item.icon), contentDescription = item.title) },
                label = { Text(text = item.title) },
                selectedContentColor = Color.Black,
                unselectedContentColor = Color.Black.copy(0.4f),
                alwaysShowLabel = true,
                selected = false,
                onClick = {
                    /* Add code later */
                }
            )
        }
    }
}


@Composable
fun SearchBar(hint :String){

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

            .shadow(10.dp, shape = RoundedCornerShape(10))
            .fillMaxWidth(),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.White,
            focusedIndicatorColor = Color.White,
            unfocusedIndicatorColor = Color.White

        ),
        leadingIcon = {
            Icon(Icons.Default.Search,"")
        },
        trailingIcon = {
            Icon(Icons.Default.Star,"")
        }




        )
}

@Composable
fun CardViewDashBoard(imageId: Int,name :String,screen: Screen) {
    val navigator = LocalNavigator.currentOrThrow
    Card(shape = RoundedCornerShape(5),
    elevation = 10.dp,
        backgroundColor = Color.White,
        modifier = Modifier
            .clickable {
                navigator.push(screen)
            }
            .height(170.dp)
            .width(105.dp)
        ) {

        Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally) {

            Spacer(modifier = Modifier.height(20.dp))
            Image(
                painter = painterResource(id = imageId), contentDescription = "",
                alignment = Alignment.Center, modifier = Modifier
                    .size(100.dp)
                    .fillMaxWidth()

            )


                Text(
                    text = name,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .fillMaxSize()
                        .height(10.dp)


                )

        }






    }
}


@Composable
fun ImageCircleVectorDashBoard(imageId :Int){

    Box(
        Modifier
            .shadow(
                elevation = 10.dp,
                shape = CircleShape, clip = true
            )
            .size(30.dp)
            .background(Color.White)) {
        Image(
            contentScale = ContentScale.Crop,
            painter = painterResource(imageId),
            contentDescription = "imageContent",
//            contentScale = ContentScale.Crop, // crop the image

            modifier = Modifier
                .size(100.dp)
                .shadow(shape = CircleShape, elevation = 0.dp)
                .align(Alignment.Center)        // clip to the circle shape
        )
    }
}

@Composable
fun CardViewDashBoardNews(imageId :Int , description:String,screen: Screen) {
    val navigator = LocalNavigator.currentOrThrow
    Card(shape = RoundedCornerShape(10),
        elevation = 10.dp,
        backgroundColor = Color.White,
        modifier = Modifier
            .height(300.dp)
            .width(250.dp)
    ) {


      Column(modifier = Modifier.fillMaxSize().clickable {
                     navigator push screen
      },

          horizontalAlignment = Alignment.CenterHorizontally

          ) {
          Image(
              contentScale = ContentScale.Crop,
              modifier= Modifier
                  .fillMaxHeight(0.55f)
                  .fillMaxWidth() ,
              painter = painterResource(id = imageId), contentDescription = "")

          Spacer(modifier = Modifier.height(10.dp))

          Text(
              description,modifier = Modifier.padding(7.dp), textAlign =TextAlign.Center,
          style = TextStyle(

              fontSize = 15.sp,
              fontWeight = FontWeight.Bold
          )
          )

          Row(modifier = Modifier
              .fillMaxWidth()
              .padding(10.dp),
          horizontalArrangement = Arrangement.Center){
//              Image(
//                  modifier= Modifier
//                      ,
//                  painter = painterResource(id = R.drawable.icons_circled_play), contentDescription = "")

              Text(
                  "Click to start",
                  modifier = Modifier,
                  style = TextStyle(

                      fontSize = 18.sp,
                      fontWeight = FontWeight.Bold
                  ))
          }


      }

    }
}

@Composable
fun CreateDrawer(scaffoldState: ScaffoldState) {

    val scope = rememberCoroutineScope()
    IconButton(onClick = {
        scope.launch {
           scaffoldState.drawerState.open()
        }
    }) {

          Icon(Icons.Default.Menu,"")
    }

}

