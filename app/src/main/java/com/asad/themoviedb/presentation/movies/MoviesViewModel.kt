package com.asad.themoviedb.presentation.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.asad.core.data.Resource
import com.asad.domain.model.Movie
import com.asad.domain.model.MovieGenre
import com.asad.domain.usecase.GenreUseCase
import com.asad.domain.usecase.MovieByGenreUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MoviesViewModel(
    private val moviesByGenreUseCase: MovieByGenreUseCase,
    private val genreUseCase: GenreUseCase
) : ViewModel() {

    private val _movieResponse = MutableLiveData<PagingData<Movie>>()
    val movieResponse: LiveData<PagingData<Movie>> = _movieResponse

    private val _genreResponse = MutableLiveData<Resource<List<MovieGenre>>>()
    val genreResponse: LiveData<Resource<List<MovieGenre>>> = _genreResponse
    val loading = MutableLiveData(false)

    init {
        getGenres()
        getMoviesByGenre("")
    }

    fun getMoviesByGenre(genres: String) {
        viewModelScope.launch {
            loading.value = true
            withContext(Dispatchers.IO) {
                moviesByGenreUseCase(genres).cachedIn(viewModelScope)
            }.collectLatest {
                _movieResponse.value = it
                loading.value = false
            }
        }
    }

    fun getGenres() {
        viewModelScope.launch {
            genreUseCase().onStart { loading.value = true }.collect {
                _genreResponse.value = it
                loading.value = false
            }
        }
    }
}