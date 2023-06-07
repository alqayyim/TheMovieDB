package com.asad.data.mapper

import com.asad.core.extension.getThumbnailUrl
import com.asad.core.extension.getYoutubeUrl
import com.asad.core.network.Mapper
import com.asad.data.model.TrailerResponse
import com.asad.domain.model.Trailer
import com.asad.domain.model.TrailerModel

class TrailerMapper : Mapper<TrailerResponse, TrailerModel> {

    override fun to(t: TrailerResponse): TrailerModel {
        return with(t) {
            TrailerModel(
                id,
                results = results.filter { it.site.equals("youtube", true) }.map {
                    Trailer(
                        it.name,
                        it.key.getYoutubeUrl(),
                        it.key.getThumbnailUrl()
                    )
                },
            )
        }
    }
}
