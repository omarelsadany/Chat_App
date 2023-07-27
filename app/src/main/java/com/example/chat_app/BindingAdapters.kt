package com.example.chat_app

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout

@BindingAdapter("app:error")
fun error(textInputLayout: TextInputLayout, Error:String?){
    textInputLayout.error=Error
}
@BindingAdapter("app:imageSrc")
fun imageSrc(imageView: ImageView,image_id:Int){
imageView.setImageResource(image_id)
}