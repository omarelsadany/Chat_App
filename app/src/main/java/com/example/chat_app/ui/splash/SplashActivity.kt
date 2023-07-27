package com.example.chat_app.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import com.example.chat_app.DataUtils
import com.example.chat_app.R
import com.example.chat_app.database.signIn
import com.example.chat_app.model.AppUser
import com.example.chat_app.ui.home.HomeActivity
import com.example.chat_app.ui.login.LoginActivity
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler(Looper.getMainLooper())
            .postDelayed({
                checkLoggedInUser()
            },2000)
    }

    private fun checkLoggedInUser() {
        val fireBaseUser=Firebase.auth.currentUser
        if(fireBaseUser==null){
            startLoginActivity()
        }else{
            //retrieve user from fireStore
            signIn(fireBaseUser.uid,
            onSuccessListener = {
                val user=it.toObject(AppUser::class.java)
                DataUtils.user=user
                startHomeActivity()
            }, onFailureListener = {
               Toast.makeText(this,"can't login",Toast.LENGTH_LONG).show()
                    startLoginActivity()
                })

        }
    }

    private fun startLoginActivity() {
        val intent=Intent(this,LoginActivity::class.java)
        startActivity(intent)
    }
    private fun startHomeActivity() {
        val intent=Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }
}