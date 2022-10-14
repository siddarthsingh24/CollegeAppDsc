package com.example.collegeappproject.models

import androidx.annotation.DrawableRes
import com.example.collegeappproject.R


//this is the model of ViewPagerItem
data class ViewPagerModel(
    @DrawableRes
    val image :Int,
    val des :String
){

    companion object{
        val data = listOf(
            ViewPagerModel(R.drawable.undraw_add_notes,"Lectures"),
            ViewPagerModel(R.drawable.undraw_reading,"Assignment"),
            ViewPagerModel(R.drawable.undraw_professor,"E-Books")
        )
    }

}