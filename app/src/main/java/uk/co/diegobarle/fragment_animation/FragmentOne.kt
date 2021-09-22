package uk.co.diegobarle.fragment_animation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import uk.co.diegobarle.fragment_animation.databinding.FragmentOneBinding

const val SHARED_IMAGE_NAME = "sharedImage"
class FragmentOne: Fragment() {

    private var _binding: FragmentOneBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOneBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnToFragmentTwo.setOnClickListener {
            navigateToSecondScreen()
        }
    }

    private fun navigateToSecondScreen() {
        val direction = FragmentOneDirections.actionFragmentOneToFragmentTwo()

        binding.image.transitionName = SHARED_IMAGE_NAME
        findNavController().navigate(
            direction.actionId,
            direction.arguments,
            null,
            //FragmentNavigatorExtras(binding.image to SHARED_IMAGE_NAME) //The origin of the bug - not passing a shared element
        )
    }
}