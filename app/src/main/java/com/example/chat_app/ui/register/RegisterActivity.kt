package com.example.chat_app.ui.register

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.chat_app.R
import com.example.chat_app.databinding.ActivityRegisterBinding
import com.example.chat_app.ui.base.BaseActivity
import com.example.chat_app.ui.home.HomeActivity

class RegisterActivity :BaseActivity<ActivityRegisterBinding,RegisterViewModel>(),Navigator {
    override fun getLayoutId(): Int {
        return R.layout.activity_register
    }

    override fun initViewModel(): RegisterViewModel {
        return ViewModelProvider(this).get(RegisterViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding.vm=viewModel
        viewModel.navigator=this

    }

    override fun openHome() {
        val intent=Intent(this,HomeActivity::class.java)
        startActivity(intent)
    }

}