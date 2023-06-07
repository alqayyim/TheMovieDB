package com.asad.domain.model

data class MovieGenre(
    val id: Int,
    val name: String,
    var isSelected : Boolean = false
) {
    override fun toString(): String {
        return id.toString()
    }
}
