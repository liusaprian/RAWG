package app.liusaprian.rawg.review

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import app.liusaprian.core.domain.model.Review
import app.liusaprian.core.utils.ReviewDiffUtils
import app.liusaprian.rawg.R
import app.liusaprian.rawg.databinding.ReviewItemBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import java.util.ArrayList

class ReviewAdapter : RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder>() {

    private var listData = ArrayList<Review>()

    fun setData(newListData: List<Review>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    inner class ReviewViewHolder(private val binding: ReviewItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Review) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(if(data.reviewerAvatar == null) R.drawable.ic_person else if(data.reviewerAvatar!!.contains("https")) data.reviewerAvatar!!.drop(1) else "https://image.tmdb.org/t/p/original${data.reviewerAvatar}")
                    .thumbnail(0.5f)
                    .skipMemoryCache(true)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(avatarIv)
                authorTv.text = data.author
                reviewRatingBar.rating = (data.rating!! /2).toFloat()
                reviewRatingTv.text = (data.rating!! /2).toString()
                contentTv.text = data.content
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ReviewViewHolder(
            ReviewItemBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        holder.bind(listData[position])
    }

    override fun getItemCount() = listData.size
}