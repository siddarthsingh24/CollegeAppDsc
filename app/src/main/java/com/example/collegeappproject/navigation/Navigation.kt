package com.example.collegeappproject.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

//Navigation work has to be done over here
@Composable
fun Navigation(navHostController: NavHostController){

       val navController = rememberNavController()

    NavHost(navController =navHostController , startDestination =NavigationRoutes.HomeScreen.route ){

        composable(NavigationRoutes.HomeScreen.route){

        }
        composable(NavigationRoutes.NotesScreen.route){

        }
        composable(NavigationRoutes.AssignmentScreen.route){

        }
        composable(NavigationRoutes.NewsScreen.route){

        }
        composable(NavigationRoutes.NoticeScreen.route){

        }



    }
}


