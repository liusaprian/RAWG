package app.liusaprian.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    val id: Int,
    val overview: String,
    val title: String,
    val genre: String,
    val posterPath: String,
    val backdropPath: String,
    val releaseDate: String,
    val rating: Double,
    var isFavorite: Boolean,
    val category: String,
) : Parcelable