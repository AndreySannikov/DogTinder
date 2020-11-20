package ru.degus.doginder.util

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso

@BindingAdapter("app:url", "app:errorImage")
fun loadImage(
    view: ImageView?,
    url: String?,
    errorImage: Drawable?
) {
    errorImage?.let { Picasso.get().load(url).error(it).fit().into(view) }
}

fun parseBreed(message : String) : String {
    val fullName = message.substringAfterLast( "/")
    return fullName.substringBeforeLast(".")
}

fun String.breed(): String {
    return this.substringBeforeLast( "/").substringAfterLast("/")
}