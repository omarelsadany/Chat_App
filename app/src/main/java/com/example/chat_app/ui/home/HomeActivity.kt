package com.example.chat_app.ui.home

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.chat_app.Constants
import com.example.chat_app.model.Room
import com.example.chat_app.R
import com.example.chat_app.database.getRoom
import com.example.chat_app.databinding.ActivityHomeBinding
import com.example.chat_app.ui.addRoom.AddRoomActivity
import com.example.chat_app.ui.base.BaseActivity
import com.example.chat_app.ui.chat.ChatActivity

class HomeActivity :BaseActivity<ActivityHomeBinding,HomeViewModel>(),Navigator {
    val adapter=RoomAdapter(null)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       viewDataBinding.vm=viewModel
       viewModel.navigator=this
        observeData()
        initRecyclerview()
    }

    private fun initRecyclerview() {
        adapter.onItemClickListener=object :RoomAdapter.OnItemClickListener{
            override fun onItemClick(position: Int, room: Room) {
              startChatActivity(room)
            }

        }
        viewDataBinding.RoomRecycler.adapter=adapter
    }

    private fun startChatActivity(room: Room) {
        val intent=Intent(this,ChatActivity::class.java)
        intent.putExtra(Constants.EXTRA_ROOM,room)
        startActivity(intent)
    }

    override fun onStart() {
        super.onStart()
        viewModel.getRooms()
    }
  fun observeData(){
      viewModel.roomLiveData.observe(this, Observer {
          adapter.chageData(it)
      })
  }
    override fun getLayoutId(): Int {
        return R.layout.activity_home
    }

    override fun initViewModel(): HomeViewModel {
        return ViewModelProvider(this)[HomeViewModel::class.java]
    }

    override fun gotoAddRoom() {
        val intent=Intent(this, AddRoomActivity::class.java)
        startActivity(intent)
    }
}