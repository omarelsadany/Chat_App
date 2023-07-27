package com.example.chat_app.ui.addRoom

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.example.chat_app.model.Category
import com.example.chat_app.model.Room
import com.example.chat_app.database.addRoom
import com.example.chat_app.ui.base.BaseViewModel

class AddRoomViewModel:BaseViewModel<Navigator>() {

    val nameRoom=ObservableField<String>()
    val nameRoomError=ObservableField<String>()
    val descriptionRoom=ObservableField<String>()
    val descriptionRoomError=ObservableField<String>()
    val categories=Category.getCategoriesList()
    var selectedCategory=categories[0]
    val roomAdded=MutableLiveData<Boolean>()


    fun createRoom(){
         if(Validate()) {
             val room = Room(
                 name = nameRoom.get(),
                 desc = descriptionRoom.get(),
                 category_id = selectedCategory.id
             )
             showLoading.value=true
             addRoom(room, onSuccessListener =  {
              showLoading.value=false
                 roomAdded.value=true
             }, onFailureListener = {
             showLoading.value=false
                 messageLiveData.value=it.localizedMessage
             })
         }
    }

    fun Validate():Boolean{
       var isvalid=true
        if(nameRoom.get().isNullOrBlank()){
          nameRoomError.set("Please enter room name")
            isvalid=false
      }else{
          nameRoomError.set(null)
      }
        if(descriptionRoom.get().isNullOrBlank()){
            descriptionRoomError.set("Please enter room description")
            isvalid=false
        }else{
            descriptionRoomError.set(null)
        }
        return isvalid
    }
}