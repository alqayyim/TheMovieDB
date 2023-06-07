package com.asad.themoviedb.presentation.reviews

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.asad.domain.model.Review
import com.asad.themoviedb.R
import com.asad.themoviedb.databinding.ItemReviewBinding
import com.bumptech.glide.Glide

class ReviewAdapter :
    PagingDataAdapter<Review, ReviewAdapter.ReviewViewHolder>(ReviewDiffCallBack) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_review, parent, false)
        val binding = ItemReviewBinding.bind(view)
        return ReviewViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        getItem(position)?.let { movie ->
            holder.bind(movie)
        }
    }

    class ReviewViewHolder(val binding: ItemReviewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Review) {
            binding.apply {
                tvContent.text = data.content
                tvRating.text = data.rating.toString()
                tvUsername.text = data.author
                Glide.with(binding.root.context)
                    .load(data.urlAvatar)
                    .circleCrop()
                    .placeholder(R.drawable.image_placeholder)
                    .into(ivAvatar)
            }
        }
    }

    object ReviewDiffCallBack : DiffUtil.ItemCallback<Review>() {
        override fun areItemsTheSame(oldItem: Review, newItem: Review): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Review, newItem: Review): Boolean =
            oldItem == newItem
    }
}
