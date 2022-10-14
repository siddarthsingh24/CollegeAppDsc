package com.example.collegeappproject.models




data class NotesModel(val item :FirestoreItem?,val key :String?){

    data class FirestoreItem(
        val name :String?= ""
    ,   val link:String?="")
}

