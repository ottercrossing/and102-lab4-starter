package com.codepath.hippo

import android.support.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class KnownFor(
    @SerialName("original_title")
    val title: String? = null, //SET TO NULL in case "original_title" is missing in a JSON entry so no errors
    @SerialName("overview")
    val overview: String? = null,
    @SerialName("backdrop_path")
    val urlPoster: String?
) : java.io.Serializable

