package com.asad.core.data


sealed class NetworkState{
    object LOADING: NetworkState()
    object SUCCESS: NetworkState()
}
