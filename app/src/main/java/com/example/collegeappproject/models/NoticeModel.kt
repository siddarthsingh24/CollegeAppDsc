package com.example.collegeappproject.models

data class NoticeModel(
    val noticeText :String,
    var noticeLink :String
){

    init {
       noticeLink= "http://msit.in" + noticeLink
    }
}