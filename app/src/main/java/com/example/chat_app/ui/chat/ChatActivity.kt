package com.example.chat_app.ui.chat

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.chat_app.Constants
import com.example.chat_app.R
import com.example.chat_app.database.getMessagesRef
import com.example.chat_app.databinding.ActivityChatBinding
import com.example.chat_app.model.Message
import com.example.chat_app.model.Room
import com.example.chat_app.ui.base.BaseActivity
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.Query

class ChatActivity : BaseActivity<ActivityChatBinding,ChatViewModel>() {
    lateinit var room:Room
    lateinit var layoutManager: LinearLayoutManager
    val adapter=MessagesAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       room=intent.getParcelableExtra<Room>(Constants.EXTRA_ROOM)!!
        viewModel.room=room
        viewDataBinding.vmChat=viewModel
        layoutManager= LinearLayoutManager(this)
        layoutManager.stackFromEnd=true
        viewDataBinding.RecyclerMessages.layoutManager=layoutManager
        viewDataBinding.RecyclerMessages.adapter=adapter
        listenForMessagesUpdates()
    }


    fun listenForMessagesUpdates(){
        getMessagesRef(room.id!!)
            .orderBy("dateTime",Query.Direction.ASCENDING)
            .addSnapshotListener{snapshots,exception->
                if(exception!=null){
                    Toast.makeText(this,"can't retreive message",Toast.LENGTH_LONG).show()
                }else{
                    val newMessagesList= mutableListOf<Message>()
                    for (dc in snapshots!!.documentChanges) {
                        when (dc.type) {
                            DocumentChange.Type.ADDED->{
                               val message=  dc.document.toObject(Message::class.java)
                                newMessagesList.add(message)
                            }

                            DocumentChange.Type.MODIFIED -> TODO()
                            DocumentChange.Type.REMOVED -> TODO()
                        }
                    }
                    adapter.appendMessages(newMessagesList)
                    viewDataBinding.RecyclerMessages.smoothScrollToPosition(adapter.itemCount)
                }
            }
    }
    override fun getLayoutId(): Int {
        return R.layout.activity_chat
    }

    override fun initViewModel(): ChatViewModel {
        return ViewModelProvider(this)[ChatViewModel::class.java]
    }
}