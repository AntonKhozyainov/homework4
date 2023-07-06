package ru.khozyainov.homework4

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition

fun Fragment.navigation() = this.context as Navigator

fun Context.setImageWithGlide(view: ImageView, imageUrl: String){
    Glide.with(this)
        .asBitmap()
        .load(imageUrl)
        .circleCrop()
        .into(object : CustomTarget<Bitmap>() {

            override fun onLoadStarted(placeholder: Drawable?) {
                super.onLoadStarted(placeholder)
                view.setImageDrawable(
                    AppCompatResources.getDrawable(this@setImageWithGlide, android.R.drawable.stat_sys_download_done)
                )
                view.imageTintList = ColorStateList.valueOf(Color.GREEN)
            }

            override fun onLoadFailed(errorDrawable: Drawable?) {
                super.onLoadFailed(errorDrawable)
                view.setImageDrawable(
                    AppCompatResources.getDrawable(this@setImageWithGlide, android.R.drawable.stat_notify_error)
                )
                view.imageTintList = ColorStateList.valueOf(Color.RED)
            }

            override fun onResourceReady(
                resource: Bitmap,
                transition: Transition<in Bitmap>?
            ) {
                view.setImageBitmap(resource)
                view.imageTintList = null
            }

            override fun onLoadCleared(placeholder: Drawable?) {}

        })
}