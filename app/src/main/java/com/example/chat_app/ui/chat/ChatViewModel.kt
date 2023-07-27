package com.example.chat_app.ui.chat

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.example.chat_app.DataUtils
import com.example.chat_app.database.addMessages
import com.example.chat_app.model.Message
import com.example.chat_app.model.Room
import com.example.chat_app.ui.base.BaseViewModel
import java.util.Date

class ChatViewModel :BaseViewModel<Navigator>(){
  val messageSentReceiveField=ObservableField<String>()
  val toastLiveData=MutableLiveData<String>()
  var room: Room?=null
    fun sentMessage(){

            val message=Message(
                messageContent = messageSentReceiveField.get(),
                roomId =room?.id,
                senderId = DataUtils.user?.id,
                senderName = DataUtils.user?.username,
                dateTime = Date().time
            )
            addMessages(message,
                onSuccessListener = {
                    messageSentReceiveField.set(null)

                },
                onFailureListener = {
                    toastLiveData.value="Something went wrong, try again later"
                })

        }


    }
