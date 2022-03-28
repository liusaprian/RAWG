package app.liusaprian.rawg.detail

import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import app.liusaprian.core.data.Resource
import app.liusaprian.core.domain.model.Image
import app.liusaprian.core.domain.model.Movie
import app.liusaprian.rawg.R
import app.liusaprian.rawg.databinding.FragmentDetailBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import org.koin.android.viewmodel.ext.android.viewModel

class DetailFragment : Fragment() {

    private val detailViewModel: DetailViewModel by viewModel()
    private val detailFragmentArgs: DetailFragmentArgs by navArgs()
    private lateinit var movie: Movie
    private lateinit var screenshotAdapter: ScreenshotAdapter

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movie = detailFragmentArgs.movie
        screenshotAdapter = ScreenshotAdapter()
        initDetailData()
    }

    private fun initDetailData() {
        with(binding) {
            with(detail) {
                Glide.with(requireActivity())
                    .load(movie.backdropPath)
                    .listener(object : RequestListener<Drawable> {
                        override fun onLoadFailed(
                            e: GlideException?,
                            model: Any?,
                            target: Target<Drawable>?,
                            isFirstResource: Boolean
                        ): Boolean {
                            backdropProgressBar.visibility = View.GONE
                            return false
                        }

                        override fun onResourceReady(
                            resource: Drawable?,
                            model: Any?,
                            target: Target<Drawable>?,
                            dataSource: DataSource?,
                            isFirstResource: Boolean
                        ): Boolean {
                            backdropProgressBar.visibility = View.GONE
                            return false
                        }
                    })
                    .into(detailImage)
                backBtn.setOnClickListener { requireActivity().onBackPressed() }
                var statusFavorite = movie.isFavorite
                setStatusFavorite(statusFavorite)
                bookmarkBtn.setOnClickListener {
                    statusFavorite = !statusFavorite
                    detailViewModel.setFavoriteGame(movie, statusFavorite)
                    setStatusFavorite(statusFavorite)
                }
            }
            with(bottomSheet) {
                nameTv.text = movie.title
                Glide.with(requireActivity())
                    .load(movie.posterPath)
                    .into(gameImage)
                genreTv.text = movie.genre
                ratingTv.text = movie.rating.toString()
                releaseDateTv.text = movie.releaseDate
                detailViewModel.getMovieImages(movie.id).observe(viewLifecycleOwner, { images ->
                    if(images != null) {
                        when (images) {
                            is Resource.Loading -> screenshotProgressBar.visibility = View.VISIBLE
                            is Resource.Success -> {
                                screenshotProgressBar.visibility = View.GONE
                                screenshotAdapter.setData(images.data)
                            }
                            is Resource.Error -> {
                                screenshotAdapter.setData(listOf(Image(movie.id, movie.backdropPath), Image(movie.id, movie.backdropPath)))
                                screenshotProgressBar.visibility = View.GONE
                            }
                        }
                    }
                })
                screenshotsRv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                screenshotsRv.adapter = screenshotAdapter
                descTv.text = movie.overview
                descTv.setOnClickListener {
                    val action = DetailFragmentDirections.descriptionAction(movie.overview)
                    findNavController().navigate(action)
                }
                bigRatingTv.text = movie.rating.toString()
                ratingBar.rating = movie.rating.toFloat()
                detailViewModel.getMovieReviews(movie.id).observe(viewLifecycleOwner, { reviews ->
                    if(reviews != null) {
                        when (reviews) {
                            is Resource.Loading -> {
                                reviewProgressNar.visibility = View.VISIBLE
                            }
                            is Resource.Success -> {
                                reviewProgressNar.visibility = View.GONE
                                reviewCountTv.text = getString(R.string.review_count, reviews.data?.size)
                                viewAllTv.setOnClickListener {
                                    if(reviews.data?.size!! > 0) {
                                        val action = DetailFragmentDirections.reviewAction(reviews = reviews.data!!.toTypedArray(), movieName = movie.title)
                                        findNavController().navigate(action)
                                    } else Toast.makeText(context, "No Reviews", Toast.LENGTH_SHORT).show()
                                }
                            }
                            is Resource.Error -> {
                                reviewProgressNar.visibility = View.GONE
                                reviewCountTv.text = getString(R.string.review_count, 0)
                            }
                        }
                    }
                })
            }
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding.detail.bookmarkBtn.setImageDrawable(ContextCompat.getDrawable(requireActivity(), R.drawable.ic_bookmark))
        } else {
            binding.detail.bookmarkBtn.setImageDrawable(ContextCompat.getDrawable(requireActivity(), R.drawable.ic_bookmark_border))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}