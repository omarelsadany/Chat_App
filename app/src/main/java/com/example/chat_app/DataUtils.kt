package com.example.chat_app

import com.example.chat_app.model.AppUser
import com.google.firebase.firestore.auth.User
import com.google.firebase.ktx.Firebase

object DataUtils {
    var user:AppUser?=null
    var firebase_user:User?=null
}