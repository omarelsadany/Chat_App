package com.example.chat_app.ui.home

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.chat_app.database.getRoom
import com.example.chat_app.model.Room
import com.example.chat_app.ui.base.BaseViewModel

class HomeViewModel:BaseViewModel<Navigator>() {

val roomLiveData=MutableLiveData<List<Room>>()



    fun getRooms(){
        getRoom(
            onSuccessListener = {querysnapshot->
                val rooms= querysnapshot.toObjects(Room::class.java)
                roomLiveData.value=rooms
            },
            onFailureListener ={
               messageLiveData.value=it.localizedMessage
            }
        )
    }
   fun createRoom(){
       navigator?.gotoAddRoom()
   }


}