package com.example.chat_app.model

import com.example.chat_app.R

data class Category (
    val id:String?=null,
    val name:String?=null,
    val imageId:Int?=null
        ){
    companion object{
        const val Music="Music"
        const val Sports="Sports"
        const val Movies="Movies"
        fun fromId(catid: String?):Category{
             when(catid){
                 Music ->{
                     return Category(
                         Music,
                         name = "Music",
                         imageId = R.drawable.music_category
                     )
                 }
                 Sports ->{
                     return Category(
                         Sports,
                         name = "Sports",
                         imageId = R.drawable.sports_category
                     )
                 }else->{
                     return Category(
                         Movies,
                         name = "Movies",
                         imageId = R.drawable.movie_image
                     )
                 }
             }
        }
        fun getCategoriesList():List<Category>{
              return  listOf(
                  fromId(Music),
                  fromId(Sports),
                  fromId(Movies)
              )
        }
    }

}