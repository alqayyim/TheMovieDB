package com.asad.data.mapper

import com.asad.core.extension.getAvatarUrl
import com.asad.core.network.Mapper
import com.asad.data.model.ReviewResponse
import com.asad.data.model.ReviewResponseItem
import com.asad.domain.model.Review
import com.asad.domain.model.ReviewModel

class ReviewMapper : Mapper<ReviewResponse, ReviewModel> {

    override fun to(t: ReviewResponse): ReviewModel {
        return with(t) {
            ReviewModel(
                total,
                page,
                results = results.map {
                    Review(
                        it.id,
                        it.author,
                        it.content,
                        it.authorDetail.rating,
                        it.authorDetail.username,
                        it.authorDetail.avatar_path?.getAvatarUrl()
                    )
                },
            )
        }
    }


    fun toReview(t: ReviewResponseItem): Review {
        return with(t) {
            Review(
                id,
                author,
                content,
                authorDetail.rating,
                authorDetail.username,
                authorDetail.avatar_path?.getAvatarUrl()
            )
        }
    }
}
