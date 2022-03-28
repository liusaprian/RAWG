package app.liusaprian.rawg.detail

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.liusaprian.core.databinding.HorizontalImageItemBinding
import app.liusaprian.core.domain.model.Image
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import java.util.ArrayList

class ScreenshotAdapter : RecyclerView.Adapter<ScreenshotAdapter.ScreenshotViewHolder>() {

    private var listData = ArrayList<Image>()

    fun setData(newListData: List<Image>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    inner class ScreenshotViewHolder(private val binding: HorizontalImageItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Image) {
            Glide.with(itemView.context)
                .load(data.filePath)
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        binding.progressBar.visibility = View.GONE
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        binding.progressBar.visibility = View.GONE
                        return false
                    }
                })
                .into(binding.ivItemImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ScreenshotViewHolder(HorizontalImageItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ScreenshotViewHolder, position: Int) {
        holder.bind(listData[position])
    }

    override fun getItemCount() = listData.size
}