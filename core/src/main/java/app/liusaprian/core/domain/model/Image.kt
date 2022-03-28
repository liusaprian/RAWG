package app.liusaprian.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Image (
    val movieId: Int,
    val filePath: String,
) : Parcelable