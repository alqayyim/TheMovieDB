package com.asad.core.extension

import android.widget.ImageView
import com.bumptech.glide.Glide

/**
 * @author asadurrahman.qayyim
 * @date 07-Jun-2023
 */

fun ImageView.loadImage(img: String?) {
    Glide.with(this).load(img).into(this)
}