package com.example.chat_app.ui.addRoom

import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import androidx.lifecycle.ViewModelProvider
import com.example.chat_app.R
import com.example.chat_app.databinding.ActivityAddRoomBinding
import com.example.chat_app.ui.base.BaseActivity

class AddRoomActivity : BaseActivity<ActivityAddRoomBinding,AddRoomViewModel>(),Navigator {
    lateinit var  adapter: CategoriesSpinnerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewDataBinding.vm=viewModel
        viewModel.navigator=this

            adapter= CategoriesSpinnerAdapter(viewModel.categories)
          viewDataBinding.spinnerCategory.adapter=adapter
          viewDataBinding.spinnerCategory.onItemSelectedListener=object :OnItemSelectedListener{
              override fun onItemSelected(
                  parent: AdapterView<*>?,
                  view: View?,
                  position: Int,
                  id: Long
              ) {
                  viewModel.selectedCategory=viewModel.categories[position]
              }

              override fun onNothingSelected(parent: AdapterView<*>?) {
                  TODO("Not yet implemented")
              }

          }
        viewModel.roomAdded.observe(this) { added ->
            if (added) {
               showDialog("Room Added Successfully", posActionName ="ok",
               posAction = DialogInterface.OnClickListener { dialog, which ->

                   dialog.dismiss()
                   finish()
               },
               cancelable = false)
            }
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_add_room
    }

    override fun initViewModel(): AddRoomViewModel {
       return ViewModelProvider(this)[AddRoomViewModel::class.java]
    }
}