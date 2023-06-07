package com.asad.core.extension

fun String.getAvatarUrl(): String {
    val split = this.split("/")
    return if (this.isEmpty()) "" else "https://secure.gravatar.com/avatar/${split[split.size-1]}"
}

fun String.getPosterUrl(): String {
    return "https://image.tmdb.org/t/p/original$this"
}
