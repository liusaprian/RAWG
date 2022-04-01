package app.liusaprian.core.utils

import androidx.annotation.Nullable
import androidx.recyclerview.widget.DiffUtil
import app.liusaprian.core.domain.model.Review

class ReviewDiffUtils(private val oldList: List<Review>, private val newList: List<Review>) :
    DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldPosition: Int, newPosition: Int): Boolean {
        val (_,
            _,
            reviewerAvatar,
            rating,
            author,
            content) = oldList[oldPosition]

        val (_,
            _,
            reviewerAvatar1,
            rating1,
            author1,
            content1) = newList[newPosition]

        return reviewerAvatar == reviewerAvatar1
                && rating == rating1
                && author == author1
                && content == content1
    }

    @Nullable
    override fun getChangePayload(oldPosition: Int, newPosition: Int): Any? {
        return super.getChangePayload(oldPosition, newPosition)
    }
}