package com.asad.themoviedb.presentation.reviews

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.asad.domain.model.Review
import com.asad.domain.usecase.ReviewUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ReviewViewModel(private val reviewUseCase: ReviewUseCase) : ViewModel() {

    private val _reviewResponse = MutableLiveData<PagingData<Review>>()
    val reviewResponse : LiveData<PagingData<Review>> = _reviewResponse
    val loading = MutableLiveData(false)

    fun getReviews(movieId: Int) {
        viewModelScope.launch {
            loading.value = true
            withContext(Dispatchers.IO) {
                reviewUseCase(movieId).cachedIn(viewModelScope)
            }.collectLatest {
                _reviewResponse.value = it
                loading.value = false
            }
        }
    }
}