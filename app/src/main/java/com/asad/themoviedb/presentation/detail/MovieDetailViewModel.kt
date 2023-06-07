package com.asad.themoviedb.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.asad.core.data.Resource
import com.asad.domain.model.MovieDetailModel
import com.asad.domain.model.TrailerModel
import com.asad.domain.usecase.MovieDetailUseCase
import com.asad.domain.usecase.TrailerUseCase
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class MovieDetailViewModel(
    private val movieDetailUseCase: MovieDetailUseCase,
    private val trailerUseCase: TrailerUseCase
) : ViewModel() {

    private val _movieDetailResponse = MutableLiveData<Resource<MovieDetailModel>>()
    val movieDetailResponse: LiveData<Resource<MovieDetailModel>> = _movieDetailResponse

    private val _trailerResponse = MutableLiveData<Resource<TrailerModel>>()
    val trailerResponse: LiveData<Resource<TrailerModel>> = _trailerResponse

    private val _loading = MutableLiveData<Boolean>()
    val loading = _loading

    fun getDetailMovie(movieId: Int) {
        viewModelScope.launch {
            movieDetailUseCase(movieId).onStart { loading.value = true }.collect {
                when (it) {
                    is Resource.Success -> {
                        _movieDetailResponse.value = it
                    }
                    else -> {}
                }
                loading.value = false
            }
        }
    }

    fun getTrailer(movieId: Int) {
        viewModelScope.launch {
            trailerUseCase(movieId).onStart { loading.value = true }.collect {
                when (it) {
                    is Resource.Success -> {
                        _trailerResponse.value = it
                    }
                    is Resource.Error -> {}
                    else -> {}
                }
                loading.value = false
            }
        }
    }
}