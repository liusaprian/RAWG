package app.liusaprian.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import app.liusaprian.favorite.databinding.FragmentFavoriteBinding
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules

class FavoriteFragment : Fragment() {

    private val favoriteViewModel: FavoriteViewModel by viewModel()
    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadKoinModules(favoriteModule)
        val favoriteItemAdapter = FavoriteItemAdapter()
        favoriteItemAdapter.onItemClick = { movie ->
            val action = FavoriteFragmentDirections.nextAction(movie)
            findNavController().navigate(action)
        }
        favoriteItemAdapter.onFavoriteClick = { movie ->
            favoriteViewModel.setFavoriteGame(movie, false)
        }

        favoriteViewModel.games.observe(viewLifecycleOwner, { movies ->
            if(movies != null && movies.isNotEmpty()) {
                favoriteItemAdapter.setData(movies)
                binding.favoriteRv.visibility = View.VISIBLE
                binding.emptyFavText.visibility = View.GONE
            } else {
                binding.favoriteRv.visibility = View.GONE
                binding.emptyFavText.visibility = View.VISIBLE
            }
        })
        with(binding.favoriteRv) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            adapter = favoriteItemAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        unloadKoinModules(favoriteModule)
        _binding = null
    }
}