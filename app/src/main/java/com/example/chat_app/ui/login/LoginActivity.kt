package com.example.chat_app.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.chat_app.R
import com.example.chat_app.databinding.ActivityLoginBinding
import com.example.chat_app.ui.base.BaseActivity
import com.example.chat_app.ui.base.BaseViewModel
import com.example.chat_app.ui.home.HomeActivity
import com.example.chat_app.ui.register.RegisterActivity
import com.example.chat_app.ui.register.RegisterViewModel

class LoginActivity: BaseActivity<ActivityLoginBinding, LoginViewModel>(),Navigator {
    override fun getLayoutId(): Int {
     return  R.layout.activity_login
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding.vm=viewModel
        viewModel.navigator=this
    }
    override fun initViewModel(): LoginViewModel {
       return  ViewModelProvider(this).get(LoginViewModel::class.java)
    }

    override fun openHomeScreen() {
        val intent= Intent(this,HomeActivity::class.java)
        startActivity(intent)
    }
    override fun openRegisterScreen() {
        val intent= Intent(this,RegisterActivity::class.java)
        startActivity(intent)
    }

}