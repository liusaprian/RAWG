package app.liusaprian.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import app.liusaprian.core.R
import app.liusaprian.core.databinding.PopularGameItemBinding
import app.liusaprian.core.domain.model.Movie
import app.liusaprian.core.utils.MovieDiffUtils
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import java.util.ArrayList

class PopularMovieAdapter : RecyclerView.Adapter<PopularMovieAdapter.PopularGameViewHolder>() {

    private var listData = ArrayList<Movie>()
    var onItemClick: ((Movie) -> Unit)? = null

    fun setData(newListData: List<Movie>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    inner class PopularGameViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = PopularGameItemBinding.bind(itemView)
        fun bind(data: Movie) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(data.posterPath)
                    .thumbnail(0.5f)
                    .skipMemoryCache(true)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(poster)
                nameTv.text = data.title
                genreTv.text = data.genre
                descTv.text = data.overview
                ratingBar.rating = data.rating.toFloat()
                ratingTv.text = data.rating.toString()
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        PopularGameViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.popular_game_item, parent, false)
        )

    override fun onBindViewHolder(holder: PopularGameViewHolder, position: Int) {
        holder.bind(listData[position])
    }

    override fun getItemCount() = listData.size
}