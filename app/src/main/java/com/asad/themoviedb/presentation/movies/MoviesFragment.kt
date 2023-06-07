package com.asad.themoviedb.presentation.movies

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.asad.core.BaseView
import com.asad.core.data.Resource
import com.asad.core.extension.px
import com.asad.domain.model.MovieGenre
import com.asad.themoviedb.R
import com.asad.themoviedb.databinding.FragmentMoviesBinding
import com.asad.themoviedb.presentation.movies.adapter.MainGenresAdapter
import com.asad.themoviedb.presentation.movies.adapter.MovieAdapter
import com.asad.themoviedb.presentation.utils.HorizontalSpaceItemDecoration
import com.asad.themoviedb.presentation.utils.VerticalSpaceItemDecoration
import com.asad.themoviedb.presentation.utils.delegate.viewBinding
import com.google.android.flexbox.AlignItems
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MoviesFragment : Fragment(R.layout.fragment_movies), BaseView {

    private val binding by viewBinding(FragmentMoviesBinding::bind)
    private val viewModel: MoviesViewModel by viewModel()
    private lateinit var genreAdapter : MainGenresAdapter
    private var expand = true
    private val genreList: MutableList<MovieGenre> = mutableListOf()
    private val movieAdapter by lazy {
        MovieAdapter(onClick = { movieID ->
            val direction = MoviesFragmentDirections.movieDetailAction(movieID)
            findNavController().navigate(direction)
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
        setupGenreAdapter()
        observeMovieResponse()
        observeGenreResponse()
        binding.apply {
            ivArrow.setOnClickListener {
                if (expand) {
                    ivArrow.setImageResource(R.drawable.ic_arrow_up)
                    rvGenre.visibility = View.GONE
                    expand = false
                } else {
                    ivArrow.setImageResource(R.drawable.ic_arrow_down)
                    rvGenre.visibility = View.VISIBLE
                    expand = true
                }
            }
        }
    }

    private fun setupAdapter() {
        genreAdapter = MainGenresAdapter { data ->
            var selectedIndex = 0
            genreList.forEachIndexed { index, movieGenre ->
                if (movieGenre.id == data.id) {
                    selectedIndex = index
                    movieGenre.isSelected = !movieGenre.isSelected
                }
            }
            genreAdapter.notifyItemChanged(selectedIndex)
            viewModel.getMoviesByGenre(genreList.filter { it.isSelected }.toString())
        }
        binding.rvMovies.run {
            layoutManager = GridLayoutManager(requireContext(), 2)
            addItemDecoration(HorizontalSpaceItemDecoration(4.px))
            adapter = movieAdapter
        }
    }

    private fun setupGenreAdapter() {
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
            adapter = genreAdapter
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

    private fun observeGenreResponse() {
        observeData(viewModel.genreResponse) { result ->
            result?.let { data ->
                    when (data) {
                        is Resource.Success -> {
                            lifecycleScope.launch {
                                genreList.clear()
                                data.model?.let { genreList.addAll(it) }
                                genreAdapter.submitList(genreList)
                            }
                        }
                        else -> {}
                    }
                }
        }
    }
}