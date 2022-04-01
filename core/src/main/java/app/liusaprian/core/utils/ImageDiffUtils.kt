package app.liusaprian.core.utils

import androidx.annotation.Nullable
import androidx.recyclerview.widget.DiffUtil
import app.liusaprian.core.domain.model.Image

class ImageDiffUtils(private val oldList: List<Image>, private val newList: List<Image>) :
    DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].filePath == newList[newItemPosition].filePath
    }

    override fun areContentsTheSame(oldPosition: Int, newPosition: Int): Boolean {
        val (_,
            filepath) = oldList[oldPosition]

        val (_,
            filepath1) = newList[newPosition]

        return filepath == filepath1
    }

    @Nullable
    override fun getChangePayload(oldPosition: Int, newPosition: Int): Any? {
        return super.getChangePayload(oldPosition, newPosition)
    }
}