package com.asad.themoviedb.presentation.reviews

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.asad.core.BaseView
import com.asad.core.FullScreenProgressDialog
import com.asad.core.extension.navigateBack
import com.asad.themoviedb.R
import com.asad.themoviedb.databinding.FragmentReviewsBinding
import com.asad.themoviedb.presentation.utils.delegate.viewBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class ReviewsFragment : Fragment(R.layout.fragment_reviews), BaseView {
    private val binding by viewBinding(FragmentReviewsBinding::bind)
    private val viewModel: ReviewViewModel by viewModel()
    private val reviewFragmentArgs: ReviewsFragmentArgs by navArgs()
    private val reviewAdapter by lazy {
        ReviewAdapter()
    }
    private val progressDialog by lazy { FullScreenProgressDialog(requireContext()) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
        observeMovieResponse()
        observeLoading()
        binding.toolbar.setNavigationOnClickListener { navigateBack() }
        viewModel.getReviews(reviewFragmentArgs.movieId)
    }

    private fun setupAdapter() {
        binding.rvReviews.run { layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = reviewAdapter
        }
    }

    private fun observeMovieResponse() {
        observeData(viewModel.reviewResponse) { result ->
            result?.let {
                lifecycleScope.launch {
                    reviewAdapter.submitData(it)
                }
            }
        }
    }

    private fun observeLoading() {
        observeData(viewModel.loading) { result ->
            result?.let {
                if (it) progressDialog.show()
                else progressDialog.hide()
            }
        }
    }
}
