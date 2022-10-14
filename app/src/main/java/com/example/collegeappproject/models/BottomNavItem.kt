package com.example.collegeappproject.models

import androidx.compose.ui.graphics.vector.ImageVector
import com.example.collegeappproject.R


//This is the data class for bottom Navigation item
sealed class NavigationItem(var route: String, var icon: Int, var title: String) {
    object Home : NavigationItem("home", R.drawable.ic_home, "Home")
    object Movies : NavigationItem("movies", R.drawable.ic_movie, "Lectures")
    object Books : NavigationItem("books", R.drawable.ic_book, "Books")
    object Profile : NavigationItem("profile", R.drawable.ic_profile, "Profile")
}
