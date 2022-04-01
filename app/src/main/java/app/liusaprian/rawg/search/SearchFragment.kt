package app.liusaprian.rawg.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import app.liusaprian.core.domain.model.Movie
import app.liusaprian.core.ui.PopularMovieAdapter
import app.liusaprian.rawg.databinding.FragmentSearchBinding
import app.liusaprian.rawg.popular.PopularFragmentDirections
import com.bumptech.glide.Glide

class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private val searchFragmentArgs: SearchFragmentArgs by navArgs()
    private var movies: List<Movie> = listOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movies = searchFragmentArgs.movies.toList()

        binding.backBtn.setOnClickListener { requireActivity().onBackPressed() }

        binding.searchBar.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun onTextChanged(query: CharSequence, p1: Int, p2: Int, p3: Int) {
                searchMovie(query.toString())
            }
            override fun afterTextChanged(p0: Editable?) {
            }
        })
    }

    private fun searchMovie(query: String) {
        val searchMatchProduct = ArrayList<Movie>()
        searchMatchProduct.addAll(
            movies.filter { it.title.lowercase().contains(query.lowercase()) }
        )
        if(searchMatchProduct.isEmpty()) {
            binding.emptySearchText.visibility = View.VISIBLE
            binding.searchRv.visibility = View.GONE
        }
        else {
            binding.emptySearchText.visibility = View.GONE
            binding.searchRv.visibility = View.VISIBLE
            initRecyclerView(searchMatchProduct)
        }
    }

    private fun initRecyclerView(movies: List<Movie>) {
        val searchAdapter = PopularMovieAdapter()
        searchAdapter.onItemClick = { selectedData ->
            val action = PopularFragmentDirections.detailAction(selectedData)
            findNavController().navigate(action)
        }
        searchAdapter.setData(movies)
        binding.searchRv.adapter = searchAdapter
        binding.searchRv.layoutManager = LinearLayoutManager(context)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        Glide.get(requireActivity()).clearMemory()
    }

}