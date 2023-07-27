package com.example.chat_app.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Room(
    var id:String?=null,
    val name:String?=null,
    val desc:String?=null,
    val category_id:String?=null
):Parcelable{

    fun getCategoryImageId():Int?{
      return  Category.fromId(category_id?:Category.Sports).imageId
    }
   companion object{
           val COLLECTION_NAME="Rooms"
   }
}
