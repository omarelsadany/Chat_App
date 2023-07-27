package com.example.chat_app.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.chat_app.model.Room
import com.example.chat_app.R
import com.example.chat_app.databinding.ItemRoomBinding

class RoomAdapter(var items:List<Room?>?):RecyclerView.Adapter<RoomAdapter.viewholder>() {

    class viewholder(val viewDataBinding:ItemRoomBinding):ViewHolder(viewDataBinding.root){
        fun bind(room:Room?){
            viewDataBinding.item=room
            viewDataBinding.invalidateAll()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {
        val itemBinding:ItemRoomBinding=
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),R.layout.item_room,parent,false)

        return viewholder(itemBinding)
    }

    override fun getItemCount(): Int =items?.size?:0

    override fun onBindViewHolder(holder: viewholder, position: Int) {
       holder.bind(items!![position])
        onItemClickListener?.let {
            holder.itemView.setOnClickListener {
                onItemClickListener?.onItemClick(position,items!![position]!!)
            }
        }
    }

    fun chageData(rooms: List<Room>) {
            items=rooms
        notifyDataSetChanged()
    }
    interface OnItemClickListener{
        fun onItemClick(position: Int,room:Room)
    }
    var onItemClickListener:OnItemClickListener?=null
}