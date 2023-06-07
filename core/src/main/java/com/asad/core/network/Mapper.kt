package com.asad.core.network

interface Mapper<T, E> {

    fun to(t: T): E
}
