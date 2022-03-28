package app.liusaprian.core.data.source.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieImageResponse(

	@field:SerializedName("backdrops")
	val backdrops: List<BackdropsItem>,

) : Parcelable

@Parcelize
data class BackdropsItem(
	@field:SerializedName("file_path")
    var filePath: String,

	var movieId: Int,
) : Parcelable
