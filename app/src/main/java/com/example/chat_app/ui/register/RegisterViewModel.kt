package com.example.chat_app.ui.register

import android.util.Log
import androidx.databinding.ObservableField
import com.example.chat_app.DataUtils
import com.example.chat_app.model.AppUser
import com.example.chat_app.database.addUserToFireStore
import com.example.chat_app.ui.base.BaseViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegisterViewModel:BaseViewModel<Navigator>() {

    val firstName=ObservableField<String>()
    val firstErrorName=ObservableField<String>()
    val lastName=ObservableField<String>()
    val lastErrorName=ObservableField<String>()
    val userName=ObservableField<String>()
    val userErrorName=ObservableField<String>()
    val email=ObservableField<String>()
    val emailError=ObservableField<String>()
    val password=ObservableField<String>()
    val passwordError=ObservableField<String>()



 val auth= Firebase.auth

    fun createAccount(){
     if(Validate()){
        addAccount()
     }
    }

    private fun addAccount() {
        showLoading.value=true
        auth.createUserWithEmailAndPassword(email.get()!!,password.get()!!)
            .addOnCompleteListener { task->
                showLoading.value=false
                if(!task.isSuccessful){
                    messageLiveData.value=task.exception?.localizedMessage
                  //show error message
                    Log.e("firebase","failed"+task.exception?.localizedMessage)
                }else{
                 //   messageLiveData.value="Success registration"
                  //  Log.e("firebase","success registration")
                  //show success message
                    //navigator?.openHome()
                    createFireStoreUser(task.result.user?.uid)
                }
            }
    }

    private fun createFireStoreUser(uid: String?) {
        showLoading.value=true
        val user= AppUser(id=uid, firstname = firstName.get()
        , lastname = lastName.get(), username = userName.get(), email = email.get())
        addUserToFireStore(user,
            {
               showLoading.value=false
                DataUtils.user=user
                navigator?.openHome()
            },
            {
                showLoading.value=false
                messageLiveData.value=it.localizedMessage
            })
    }

    private fun Validate(): Boolean {
        var valid=true
        if(firstName.get().isNullOrBlank()){
            firstErrorName.set("enter your first name")
            valid=false
        }else{
            firstErrorName.set(null)
        }
        if(lastName.get().isNullOrBlank()){
            lastErrorName.set("enter your last name")
            valid=false
        }else{
            lastErrorName.set(null)
        }
        if(userName.get().isNullOrBlank()){
            userErrorName.set("enter your user name")
            valid=false
        }else{
            userErrorName.set(null)
        }
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
}