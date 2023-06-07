package com.asad.core.extension

import com.asad.core.data.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*


fun <T> Flow<Resource<T>>.buildWrapper() =
    catch { error ->
        emit(Resource.Error(Throwable(message = error.message)))
        emit(Resource.Loading(false))
    }
        .flowOn(Dispatchers.IO)
