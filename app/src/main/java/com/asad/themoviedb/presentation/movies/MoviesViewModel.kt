package com.asad.themoviedb.presentation.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.asad.domain.usecase.MovieUseCase
import com.asad.domain.model.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MoviesViewModel(private val moviesUseCase: MovieUseCase) : ViewModel() {

    private val _movieResponse = MutableLiveData<PagingData<Movie>>()
    val movieResponse : LiveData<PagingData<Movie>> = _movieResponse
    val loading = MutableLiveData(false)

    fun getMovies(path: String) {
        viewModelScope.launch {
            loading.value = true
            withContext(Dispatchers.IO) {
                moviesUseCase(path).cachedIn(viewModelScope)
            }.collectLatest {
                _movieResponse.value = it
                loading.value = false
            }
        }
    }
}