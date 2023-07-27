package com.example.chat_app.ui.chat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.chat_app.DataUtils
import com.example.chat_app.R
import com.example.chat_app.databinding.ItemMessageReceivedBinding
import com.example.chat_app.databinding.ItemMessageSentBinding
import com.example.chat_app.model.Message

class MessagesAdapter:Adapter<ViewHolder>() {
var items= mutableListOf<Message?>()
    val  RECEIVERD=1
    val  SENT=2
    class SentMessageViewHolder(val viewDataBinding:ItemMessageSentBinding):ViewHolder(viewDataBinding.root){
             fun Bind(message:Message?){
                 viewDataBinding.itemmessage=message
                 viewDataBinding.invalidateAll()
             }
    }


    class ReceivedMessageViewHolder(val viewDataBinding:ItemMessageReceivedBinding):ViewHolder(viewDataBinding.root){
        fun Bind(message:Message?){
            viewDataBinding.itemmessage=message
            viewDataBinding.invalidateAll()
        }
    }

    override fun getItemViewType(position: Int): Int {
       val message= items.get(position)
        if(message?.senderId==DataUtils.user?.id){
            return SENT
        }
        return RECEIVERD
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       if(RECEIVERD==viewType){
           val itemBinding:ItemMessageReceivedBinding
           =DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_message_received
               ,parent,false)
           return ReceivedMessageViewHolder(itemBinding)
       }
        val itemBinding:ItemMessageSentBinding
                =DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_message_sent
            ,parent,false)
        return SentMessageViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        return  items.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       if(holder is SentMessageViewHolder){
          holder.Bind(items.get(position))
       }else if(holder is ReceivedMessageViewHolder){
           holder.Bind(items.get(position))
       }
    }

    fun appendMessages(newMessagesList: MutableList<Message>) {
        items.addAll(newMessagesList)
        notifyItemRangeInserted(items.size,newMessagesList.size)
    }

}