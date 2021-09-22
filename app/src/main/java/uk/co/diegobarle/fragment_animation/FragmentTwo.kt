package uk.co.diegobarle.fragment_animation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewAnimationUtils
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import android.view.animation.DecelerateInterpolator
import androidx.fragment.app.Fragment
import androidx.transition.ChangeBounds
import androidx.transition.ChangeTransform
import androidx.transition.TransitionSet
import uk.co.diegobarle.fragment_animation.databinding.FragmentTwoBinding

class FragmentTwo: Fragment() {

    private var _binding: FragmentTwoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setupSharedElementsTransition()
        _binding = FragmentTwoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        startCircularRevealAnimation()
    }

    private fun setupSharedElementsTransition() {
        val imageAnimation = TransitionSet()
        with(imageAnimation) {
            addTransition(ChangeBounds())
            addTransition(ChangeTransform())

            ordering = TransitionSet.ORDERING_TOGETHER
            duration = resources.getInteger(android.R.integer.config_mediumAnimTime).toLong()
            interpolator = DecelerateInterpolator()
        }
        sharedElementEnterTransition = imageAnimation
    }

    private fun startCircularRevealAnimation() {
        val centerX = binding.viewBackground.width / 2
        val centerY = binding.viewBackground.height / 2
        val anim = ViewAnimationUtils.createCircularReveal(
            binding.viewBackground, centerX,
            centerY, 0f, binding.viewBackground.width.toFloat()
        ).apply {
            duration = resources.getInteger(android.R.integer.config_mediumAnimTime).toLong()
            interpolator = AccelerateInterpolator()
        }
        anim.start()
    }
}