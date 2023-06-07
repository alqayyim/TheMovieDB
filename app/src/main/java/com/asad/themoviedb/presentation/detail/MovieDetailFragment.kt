package com.asad.themoviedb.presentation.detail

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.asad.core.BaseView
import com.asad.core.FullScreenProgressDialog
import com.asad.core.data.Resource
import com.asad.core.extension.loadImage
import com.asad.core.extension.px
import com.asad.themoviedb.R
import com.asad.themoviedb.databinding.FragmentMovieDetailBinding
import com.asad.themoviedb.presentation.detail.adapter.MovieGenresAdapter
import com.asad.themoviedb.presentation.detail.adapter.TrailerAdapter
import com.asad.themoviedb.presentation.utils.HorizontalSpaceItemDecoration
import com.asad.themoviedb.presentation.utils.VerticalSpaceItemDecoration
import com.asad.themoviedb.presentation.utils.delegate.viewBinding
import com.google.android.flexbox.AlignItems
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieDetailFragment : Fragment(R.layout.fragment_movie_detail), BaseView {
    private val binding by viewBinding(FragmentMovieDetailBinding::bind)
    private val viewModel: MovieDetailViewModel by viewModel()
    private val movieDetailArgs: MovieDetailFragmentArgs by navArgs()
    private val progressDialog by lazy { FullScreenProgressDialog(requireContext()) }
    private val trailerAdapter by lazy {
        TrailerAdapter(onClick = { url ->
            openYoutubeIntent(url)
        })
    }
    private val movieGenresAdapter by lazy {
        MovieGenresAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeDetailMovie()
        observeTrailer()
        setMovieGenres()
        setupTrailerAdapter()
        viewModel.getDetailMovie(movieDetailArgs.movieId)
        viewModel.getTrailer(movieDetailArgs.movieId)
        binding.tvSeeReviews.setOnClickListener {
            val direction = MovieDetailFragmentDirections.reviewAction(movieDetailArgs.movieId)
            findNavController().navigate(direction)
        }
        binding.backIv.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun observeDetailMovie() {
        observeData(viewModel.movieDetailResponse) { result ->
            result?.let {
                when (it) {
                    is Resource.Success -> {
                        binding.apply {
                            tvTitle.text = it.model?.title
                            tvSummary.text = it.model?.overview
                            tvRating.text = it.model?.rating
                            tvDuration.text = "${it.model?.duration.toString()} min"
                            ivPoster.loadImage(it.model?.poster)
                            ivBackdrop.loadImage(it.model?.backdrop)
                            it.model?.genres?.let {
                                movieGenresAdapter.submitList(it)
                            }
                        }
                    }
                    is Resource.Loading -> {
                        if (it.isLoading) progressDialog.show()
                        else progressDialog.hide()
                    }
                    is Resource.Error -> {
                        progressDialog.hide()
                    }
                    else -> {}
                }
            }
        }
    }

    private fun observeTrailer() {
        observeData(viewModel.trailerResponse) { result ->
            result?.let {
                when (it) {
                    is Resource.Success -> {
                        it.model?.results?.let{
                            trailerAdapter.submitList(it)
                        }
                    }
                    else -> {}
                }
            }
        }
    }

    private fun setupTrailerAdapter() {
        binding.rvTrailer.run {
            setHasFixedSize(false)
            addItemDecoration(HorizontalSpaceItemDecoration(4.px))
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = trailerAdapter
        }
    }

    private fun setMovieGenres() {
        val flexBoxLayoutManager = FlexboxLayoutManager(context).apply {
            flexWrap = FlexWrap.WRAP
            flexDirection = FlexDirection.ROW
            alignItems = AlignItems.STRETCH
        }

        binding.rvGenre.apply {
            setHasFixedSize(false)
            addItemDecoration(HorizontalSpaceItemDecoration(4.px))
            addItemDecoration(VerticalSpaceItemDecoration(4.px))
            layoutManager = flexBoxLayoutManager
            adapter = movieGenresAdapter
        }
    }

    private fun openYoutubeIntent(url: String) {
        val webIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        try {
            requireActivity().startActivity(webIntent)
        } catch (ex: ActivityNotFoundException) {
            // do nothing
        }
    }
}
