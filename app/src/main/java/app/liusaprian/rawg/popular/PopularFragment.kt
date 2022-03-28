package app.liusaprian.rawg.popular

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import app.liusaprian.core.ui.PopularMovieAdapter
import app.liusaprian.rawg.databinding.FragmentPopularBinding

class PopularFragment : Fragment() {

    private var _binding: FragmentPopularBinding? = null
    private val binding get() = _binding!!

    private val popularFragmentArgs: PopularFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentPopularBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val popularAdapter = PopularMovieAdapter()
        popularAdapter.setData(popularFragmentArgs.movies.toList())

        popularAdapter.onItemClick = { selectedData ->
            val action = PopularFragmentDirections.detailAction(selectedData)
            findNavController().navigate(action)
        }

        with(binding) {
            backBtn.setOnClickListener {
                requireActivity().onBackPressed()
            }
            popularRv.layoutManager = LinearLayoutManager(context)
            popularRv.adapter = popularAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}