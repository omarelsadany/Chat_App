package com.example.chat_app.ui.login

import android.util.Log
import androidx.databinding.ObservableField
import com.example.chat_app.DataUtils
import com.example.chat_app.model.AppUser
import com.example.chat_app.database.signIn
import com.example.chat_app.ui.base.BaseViewModel
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginViewModel:BaseViewModel<Navigator>(){

    val email= ObservableField<String>()
    val emailError= ObservableField<String>()
    val password= ObservableField<String>()
    val passwordError= ObservableField<String>()

    val firebaseAuth=Firebase.auth
    fun login(){
         if(validate()){
            loginauthWithFireBaseAuth()
         }
    }
    fun loginauthWithFireBaseAuth(){
        showLoading.value=true
       firebaseAuth.signInWithEmailAndPassword(email.get()!!,password.get()!!)
            .addOnCompleteListener { task->
                showLoading.value=false
                if(!task.isSuccessful){
                    messageLiveData.value=task.exception?.localizedMessage
                    //show error message
                    Log.e("firebase","failed"+task.exception?.localizedMessage)
                }else{
                   // messageLiveData.value="Success registration"
                   // Log.e("firebase","success registration")
                    //show success message
                    //navigator?.openRegisterScreen()
                    checkGetUserFromFireStored(task.result.user?.uid)
                }
            }
    }

    private fun checkGetUserFromFireStored(uid:String?) {
       showLoading.value=true
        signIn(uid!!, OnSuccessListener{docSnapShot->
            showLoading.value=false
           val user=docSnapShot.toObject(AppUser::class.java)
            if(user==null){
                DataUtils.user=user
                messageLiveData.value="Invalid e-mail or password"
                return@OnSuccessListener
            }
            navigator?.openHomeScreen()
       }
        ) {
            showLoading.value = false
            messageLiveData.value = it.localizedMessage
        }
    }


    private fun validate(): Boolean {
        var valid=true
        if(email.get().isNullOrBlank()){
            emailError.set("enter your e-mail")
            valid=false
        }else{
            emailError.set(null)
        }
        if(password.get().isNullOrBlank()){
            passwordError.set("enter your password")
            valid=false
        }else{
            passwordError.set(null)
        }
        return valid
    }
    fun openRegister(){
        navigator?.openRegisterScreen()
    }
}