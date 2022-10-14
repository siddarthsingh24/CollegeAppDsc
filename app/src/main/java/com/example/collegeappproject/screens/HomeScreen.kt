package com.example.collegeappproject.screens

import android.os.Bundle
import android.util.Log
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import cafe.adriel.voyager.navigator.Navigator
import com.example.collegeappproject.R
import com.example.collegeappproject.features.webScrapping.WebScrapingViewModel
import com.example.collegeappproject.models.NoticeModel
import com.example.collegeappproject.ui.theme.CollegeAppProjectTheme
import com.example.collegeappproject.utils.ResultState
import com.example.collegeappproject.utils.UtilsFunctions
import com.example.collegeappproject.utils.ViewPagerUi
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeScreen : ComponentActivity() {

    private val webScrapingViewModel: WebScrapingViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


//


        UtilsFunctions.actionBarRemove(window)



        setContent {
            CollegeAppProjectTheme {

                Surface(color = MaterialTheme.colors.background) {


//                    DashBoardScreen()

                    Navigator(screen = DashBoardScreen(this))
                }

            }
        }
    }
}

//



@Composable
fun TopAppBarCreated(scaffoldState: ScaffoldState){

    val scope = rememberCoroutineScope()

    TopAppBar(
        modifier = Modifier.background(Color.Transparent),
        title = {
            Text(text = "Home", textAlign = TextAlign.Center, style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
            ),
                modifier = Modifier.padding(start = 100.dp),
            )
        },
        navigationIcon = {
            IconButton(onClick = { scope.launch {
                scaffoldState.drawerState.open()
            } }) {
                Icon(Icons.Filled.Menu,"Menu Icon")
            }
            //composable function for leading icon
        },
        backgroundColor = Color.Transparent,
        elevation = 0.dp,
        actions = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(Icons.Filled.Person,"Menu Icon")
            }
        },
    )

}
//
