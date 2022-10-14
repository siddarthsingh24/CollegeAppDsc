package com.example.collegeappproject.navigation

sealed class NavigationRoutes(val route :String){

    object HomeScreen :NavigationRoutes("home_screen")
    object NoticeScreen :NavigationRoutes("notice_screen")
    object NewsScreen :NavigationRoutes("news_screen")
    object NotesScreen :NavigationRoutes("notes_screen")
    object AssignmentScreen :NavigationRoutes("assignment_screen")

}