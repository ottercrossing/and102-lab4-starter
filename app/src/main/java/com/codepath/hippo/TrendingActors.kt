package com.codepath.hippo

import android.support.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable



@Keep
@Serializable
data class SearchTrendingActorsResults(
    @SerialName("results")
    val results: List<TrendingActors>?
)

/*@Keep
@Serializable
data class BaseResponse(
    @SerialName("docs")
    val docs: List<Article>?
)*/

@Keep
@Serializable
data class TrendingActors(
    @SerialName("name")
    val name: String?,
    @SerialName("profile_path")
    val profilePicURL: String?,
    @SerialName("known_for")
    val knownFor: List<KnownFor>?,
)

    : java.io.Serializable {
    val posterImageUrl = "https://image.tmdb.org/t/p/w500/${knownFor?.firstOrNull { it.urlPoster != null }?.urlPoster ?: ""}"

    val selfieImageUrl = "https://image.tmdb.org/t/p/w500/$profilePicURL"

}



