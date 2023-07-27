package com.example.chat_app.ui.addRoom

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.chat_app.model.Category
import com.example.chat_app.R

class CategoriesSpinnerAdapter(val items:List<Category>):BaseAdapter() {
    override fun getCount(): Int {
       return items.size
    }

    override fun getItem(position: Int): Any {
      return items[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var viewHolder:ViewHolder
       var myView=convertView
        if(myView==null){
            myView=LayoutInflater.from(parent!!.context)
                .inflate(R.layout.item_spinner_category,parent,false)
        viewHolder= ViewHolder(myView)
        myView.setTag(viewHolder)
        }else{
              viewHolder=myView.tag as ViewHolder
        }
        val item=items[position]
        viewHolder.title.setText(item.name)
        viewHolder.image.setImageResource(item.imageId!!)
        return myView!!
    }
    class ViewHolder(val view: View){
        val title:TextView=view.findViewById(R.id.title_in_spinner)
        val image:ImageView=view.findViewById(R.id.image_in_spinner)
    }

}