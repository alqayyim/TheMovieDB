package com.asad.core.extension

import com.asad.core.network.Mapper
import com.asad.core.data.Resource

fun <A, B> A.mapTo(mapper: Mapper<A, B>): Resource<B> {
    return Resource.Success(mapper.to(this))
}
