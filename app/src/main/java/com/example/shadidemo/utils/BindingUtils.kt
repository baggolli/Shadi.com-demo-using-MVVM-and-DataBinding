package com.example.shadidemo.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

@BindingAdapter("image")
fun loadImage(view: ImageView, listOfByteArray: ByteArray) {
    Glide.with(view)
        .load(listOfByteArray.toString(Charsets.UTF_8))
        .diskCacheStrategy(DiskCacheStrategy.DATA)
        .circleCrop()
        .into(view)
}