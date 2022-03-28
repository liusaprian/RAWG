package app.liusaprian.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Review (
    val id: String,
    val movieId: Int,
    val reviewerAvatar: String?,
    val rating: Double?,
    val author: String,
    val content: String,
) : Parcelable