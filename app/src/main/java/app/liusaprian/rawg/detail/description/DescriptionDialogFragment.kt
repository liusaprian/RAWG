package app.liusaprian.rawg.detail.description

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import app.liusaprian.rawg.databinding.FragmentDescriptionDialogBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class DescriptionDialogFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentDescriptionDialogBinding? = null
    private val binding get() = _binding!!

    private val descriptionDialogFragmentArgs: DescriptionDialogFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentDescriptionDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.desc.text = descriptionDialogFragmentArgs.description
        binding.backBtn.setOnClickListener { dismiss() }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}