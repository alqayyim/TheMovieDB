package com.asad.themoviedb.presentation.movies.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.asad.core.extension.loadImage
import com.asad.domain.model.Movie
import com.asad.themoviedb.R
import com.asad.themoviedb.databinding.ItemMovieBinding

class MovieAdapter(private val onClick: (Int) -> Unit) :
    PagingDataAdapter<Movie, MovieAdapter.MovieViewHolder>(MovieDiffCallBack) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_movie, parent, false)
        val binding = ItemMovieBinding.bind(view)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        getItem(position)?.let { movie ->
            holder.bind(movie)
            holder.itemView.setOnClickListener {
                onClick.invoke(movie.movieId)
            }
        }
    }

    class MovieViewHolder(val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Movie) {
            binding.apply {
                tvMovieName.text = data.title
                tvRating.text = data.voteAverage.toString()
                ivMovie.loadImage(data.poster)
            }
        }
    }

    object MovieDiffCallBack : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean =
            oldItem.movieId == newItem.movieId

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean =
            oldItem == newItem
    }
}
