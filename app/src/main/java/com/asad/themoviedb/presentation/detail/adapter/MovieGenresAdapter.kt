package com.asad.themoviedb.presentation.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.asad.domain.model.MovieGenre
import com.asad.themoviedb.databinding.ItemGenreBinding

class MovieGenresAdapter :
    ListAdapter<MovieGenre, MovieGenresAdapter.MovieGenresViewHolder>(MovieGenreDiffCallBack) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieGenresViewHolder {
        val view =
            ItemGenreBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieGenresViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieGenresViewHolder, position: Int) {
        getItem(position)?.let { movie ->
            holder.bind(movie)
        }
    }

    inner class MovieGenresViewHolder(private val binding: ItemGenreBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: MovieGenre) {
            binding.apply {
                tvGenre.text = item.name
            }
        }
    }

    object MovieGenreDiffCallBack : DiffUtil.ItemCallback<MovieGenre>() {
        override fun areItemsTheSame(oldItem: MovieGenre, newItem: MovieGenre): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MovieGenre, newItem: MovieGenre): Boolean {
            return oldItem == newItem
        }
    }
}
