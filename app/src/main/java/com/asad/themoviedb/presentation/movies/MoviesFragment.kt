package com.asad.themoviedb.presentation.movies

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.asad.core.BaseView
import com.asad.core.extension.px
import com.asad.themoviedb.R
import com.asad.themoviedb.databinding.FragmentMoviesBinding
import com.asad.themoviedb.presentation.movies.adapter.MovieAdapter
import com.asad.themoviedb.presentation.utils.HorizontalSpaceItemDecoration
import com.asad.themoviedb.presentation.utils.delegate.viewBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MoviesFragment : Fragment(R.layout.fragment_movies), BaseView {

    private val binding by viewBinding(FragmentMoviesBinding::bind)
    private val viewModel: MoviesViewModel by viewModel()
    private val movieAdapter by lazy {
        MovieAdapter(onClick = { movieID ->
            val direction = MoviesFragmentDirections.movieDetailAction(movieID)
            findNavController().navigate(direction)
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
        observeMovieResponse()
        viewModel.getMovies("now_playing")
    }

    private fun setupAdapter() {
        binding.rvMovies.run {
            layoutManager = GridLayoutManager(requireContext(), 2)
            addItemDecoration(HorizontalSpaceItemDecoration(4.px))
            adapter = movieAdapter
        }
    }

    private fun observeMovieResponse() {
        observeData(viewModel.movieResponse) { result ->
            result?.let {
                lifecycleScope.launch {
                    movieAdapter.submitData(it)
                }
            }
        }
    }
}