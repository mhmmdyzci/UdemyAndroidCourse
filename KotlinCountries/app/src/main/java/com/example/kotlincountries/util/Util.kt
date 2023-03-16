package com.example.kotlincountries.util

import android.content.Context
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.kotlincountries.R

fun ImageView.downloadFromUrl (url : String?, drawable: CircularProgressDrawable){
    val options = RequestOptions().placeholder(drawable).error(R.mipmap.ic_launcher_round)
    Glide.with(context)
        .setDefaultRequestOptions(options).load(url).into(this)

}

fun placeholderProgressBar(context : Context) : CircularProgressDrawable {
    return CircularProgressDrawable(context).apply {
        centerRadius = 40f
        strokeWidth = 8f
        start()

    }

}
@BindingAdapter("android:downloadUrl")
fun dowloadImage(view : ImageView, url : String?){
    view.downloadFromUrl(url, placeholderProgressBar(view.context))

}