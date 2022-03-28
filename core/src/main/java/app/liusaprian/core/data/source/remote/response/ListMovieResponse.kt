package app.liusaprian.core.data.source.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ListMovieResponse(

	@field:SerializedName("results")
	val results: List<MovieResponse>,

) : Parcelable

@Parcelize
data class MovieResponse(

	@field:SerializedName("overview")
	val overview: String,

	@field:SerializedName("title")
	val title: String,

	@field:SerializedName("genre_ids")
	val genreIds: List<Int>,

	@field:SerializedName("poster_path")
	var posterPath: String,

	@field:SerializedName("release_date")
	val releaseDate: String,

	@field:SerializedName("backdrop_path")
	var backdropPath: String,

	@field:SerializedName("vote_average")
	var voteAverage: Double,

	@field:SerializedName("id")
	val id: Int,

	var genre: String,

	var category: String,
) : Parcelable
