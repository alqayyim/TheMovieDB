package com.asad.themoviedb.utils

import androidx.lifecycle.LiveData

internal fun <T> LiveData<T>.observeOnce(onChangeHandler: (T) -> Unit) {
    val observer = OneTimeObserver(onChangeHandler)
    observe(observer, observer)
}