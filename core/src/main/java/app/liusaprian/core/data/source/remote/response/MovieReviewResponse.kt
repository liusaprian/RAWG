package app.liusaprian.core.data.source.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieReviewResponse(

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("results")
	val results: List<ReviewItem>,
) : Parcelable

@Parcelize
data class ReviewItem(

	@field:SerializedName("author_details")
	val authorDetails: AuthorDetails,

	@field:SerializedName("author")
	val author: String,

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("content")
	val content: String,

	var movieId: Int,
) : Parcelable

@Parcelize
data class AuthorDetails(
	@field:SerializedName("avatar_path")
	val avatarPath: String?,

	@field:SerializedName("rating")
	var rating: Double? = 0.0,
) : Parcelable
