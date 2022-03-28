package app.liusaprian.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "review")
data class ReviewEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,

    @ColumnInfo(name = "movie_id")
    val movieId: Int,

    @ColumnInfo(name = "rating")
    val rating: Double?,

    @ColumnInfo(name = "avatar_path")
    val reviewerAvatar: String?,

    @ColumnInfo(name = "author")
    val author: String,

    @ColumnInfo(name = "content")
    val content: String,
)
