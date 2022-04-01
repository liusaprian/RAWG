package app.liusaprian.core.utils

import androidx.annotation.Nullable
import androidx.recyclerview.widget.DiffUtil
import app.liusaprian.core.domain.model.Movie

class MovieDiffUtils(private val oldList: List<Movie>, private val newList: List<Movie>) :
    DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldPosition: Int, newPosition: Int): Boolean {
        val (_,
            overview,
            title,
            genre,
            posterPath,
            backdropPath,
            releaseDate,
            rating,
            isFavorite,
            category) = oldList[oldPosition]

        val (_,
            overview1,
            title1,
            genre1,
            posterPath1,
            backdropPath1,
            releaseDate1,
            rating1,
            isFavorite1,
            category1) = newList[newPosition]

        return overview == overview1
                && title == title1
                && genre == genre1
                && posterPath == posterPath1
                && backdropPath == backdropPath1
                && releaseDate == releaseDate1
                && rating == rating1
                && isFavorite == isFavorite1
                && category == category1
    }

    @Nullable
    override fun getChangePayload(oldPosition: Int, newPosition: Int): Any? {
        return super.getChangePayload(oldPosition, newPosition)
    }
}