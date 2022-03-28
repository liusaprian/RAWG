package app.liusaprian.rawg.review

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import app.liusaprian.rawg.databinding.FragmentReviewBinding

class ReviewFragment : Fragment() {

    private var _binding: FragmentReviewBinding? = null
    private val binding get() = _binding!!

    private val reviewFragmentArgs: ReviewFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentReviewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val reviewAdapter = ReviewAdapter()
        reviewAdapter.setData(reviewFragmentArgs.reviews.toList())

        binding.backBtn.setOnClickListener {
            requireActivity().onBackPressed()
        }
        binding.reviewMovieNameTv.text = reviewFragmentArgs.movieName
        with(binding.reviewRv) {
            layoutManager = LinearLayoutManager(context)
            adapter = reviewAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}