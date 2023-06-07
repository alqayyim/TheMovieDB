package com.asad.themoviedb.presentation.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.asad.core.extension.loadImage
import com.asad.domain.model.Trailer
import com.asad.themoviedb.databinding.ItemTrailerBinding

class TrailerAdapter(private val onClick: (Int) -> Unit) :
    ListAdapter<Trailer, TrailerAdapter.TrailersViewHolder>(TrailerDiffCallBack) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrailersViewHolder {
        val view =
            ItemTrailerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TrailersViewHolder(view)
    }

    override fun onBindViewHolder(holder: TrailersViewHolder, position: Int) {
        getItem(position)?.let { movie ->
            holder.bind(movie)
        }
    }

    inner class TrailersViewHolder(private val binding: ItemTrailerBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Trailer) {
            binding.ivThumbnail.loadImage(item.urlThumbnail)
        }
    }

    object TrailerDiffCallBack : DiffUtil.ItemCallback<Trailer>() {
        override fun areItemsTheSame(oldItem: Trailer, newItem: Trailer): Boolean {
            return oldItem.urlVideo == newItem.urlVideo
        }

        override fun areContentsTheSame(oldItem: Trailer, newItem: Trailer): Boolean {
            return oldItem == newItem
        }
    }
}
