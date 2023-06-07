package com.asad.themoviedb.presentation.movies.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.asad.domain.model.MovieGenre
import com.asad.themoviedb.R
import com.asad.themoviedb.databinding.ItemGenreMainBinding

class MainGenresAdapter(private val onClick: (MovieGenre) -> Unit) :
    ListAdapter<MovieGenre, MainGenresAdapter.MovieGenresViewHolder>(MovieGenreDiffCallBack) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieGenresViewHolder {
        val view =
            ItemGenreMainBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieGenresViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieGenresViewHolder, position: Int) {
        getItem(position)?.let { genre ->
            holder.bind(genre)
            holder.itemView.setOnClickListener {
                onClick.invoke(genre)
            }
        }
    }

    inner class MovieGenresViewHolder(private val binding: ItemGenreMainBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: MovieGenre) {
            binding.apply {
                tvGenre.text = item.name
                if (item.isSelected) {
                    itemView.setBackgroundResource(R.drawable.bg_rounded_blue)
                    tvGenre.setTextColor(ContextCompat.getColor(binding.root.context, R.color.white))
                } else {
                    itemView.setBackgroundResource(R.drawable.bg_rounded_transparent)
                    tvGenre.setTextColor(ContextCompat.getColor(binding.root.context, R.color.light_text_color))
                }
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
