package com.example.chat_app.model

data class AppUser (
    var id:String?=null,
    var username:String?=null,
    var firstname:String?=null,
    var lastname:String?=null,
    var email:String?=null
        ){
    companion object{
        const val Collection_Name="users"
    }
}