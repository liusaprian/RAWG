package app.liusaprian.rawg.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import app.liusaprian.core.data.Resource
import app.liusaprian.core.domain.model.Movie
import app.liusaprian.core.ui.PopularMovieAdapter
import app.liusaprian.core.ui.NowPlayingMovieAdapter
import app.liusaprian.rawg.databinding.FragmentHomeBinding
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModel()

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val movies: ArrayList<Movie> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movies.clear()

        val nowPlayingMovieAdapter = NowPlayingMovieAdapter()
        val popularMovieAdapter = PopularMovieAdapter()

        nowPlayingMovieAdapter.onItemClick = { selectedData ->
            val action = HomeFragmentDirections.nextAction(selectedData)
            findNavController().navigate(action)
        }

        popularMovieAdapter.onItemClick = { selectedData ->
            val action = HomeFragmentDirections.nextAction(selectedData)
            findNavController().navigate(action)
        }

        homeViewModel.nowPlayingMovie.observe(viewLifecycleOwner, { movie ->
            if(movie != null) {
                when (movie) {
                    is Resource.Success -> {
                        nowPlayingMovieAdapter.setData(movie.data)
                        movie.data?.let { movies.addAll(it) }
                    }
                }
            }
        })

        homeViewModel.popularMovie.observe(viewLifecycleOwner, { movie ->
            if(movie != null) {
                when (movie) {
                    is Resource.Success -> {
                        popularMovieAdapter.setData(movie.data)
                        movie.data?.let { movies.addAll(it) }
                        binding.seeAllTv.setOnClickListener {
                            val action = HomeFragmentDirections.popularAction(movies = movie.data!!.toTypedArray())
                            findNavController().navigate(action)
                        }
                    }
                }
            }
        })

        with(binding.popularRv) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = popularMovieAdapter
        }

        with(binding.nowPlayingRv) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            adapter = nowPlayingMovieAdapter
        }

        binding.searchBtn.setOnClickListener {
            val action = HomeFragmentDirections.searchAction(movies.toTypedArray())
            findNavController().navigate(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}