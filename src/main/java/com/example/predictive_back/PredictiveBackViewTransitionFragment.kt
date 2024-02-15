package com.example.predictive_back

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.View
import androidx.activity.BackEventCompat
import androidx.activity.OnBackPressedCallback
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.transition.ChangeBounds
import androidx.transition.Fade
import androidx.transition.TransitionManager
import androidx.transition.TransitionSeekController
import androidx.transition.TransitionSet
import com.example.android_predictive_back.R
import com.example.android_predictive_back.databinding.FragmentPredictiveBackViewTransitionBinding

class PredictiveBackViewTransitionFragment : Fragment(
    R.layout.fragment_predictive_back_view_transition
) {
    enum class ShowText { SHORT, LONG }

    private val transitionSet = TransitionSet().apply {
        addTransition(Fade(Fade.MODE_OUT))
        addTransition(ChangeBounds())
        addTransition(Fade(Fade.MODE_IN))
    }

    private var _binding: FragmentPredictiveBackViewTransitionBinding? = null
    private val binding get() = requireNotNull(_binding)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentPredictiveBackViewTransitionBinding.bind(view)

        val callback = object : OnBackPressedCallback(enabled = false) {

            var controller: TransitionSeekController? = null

            override fun handleOnBackStarted(backEvent: BackEventCompat) {
                super.handleOnBackStarted(backEvent)
                controller = TransitionManager.controlDelayedTransition(
                    binding.root,
                    transitionSet
                )
                changeTextVisibility(ShowText.SHORT)
            }

            override fun handleOnBackProgressed(backEvent: BackEventCompat) {
                super.handleOnBackProgressed(backEvent)
                // Play the transition as the user swipes back
                if (controller?.isReady == true) {
                    controller?.currentFraction = backEvent.progress
                }
            }

            override fun handleOnBackPressed() {
                // Finish playing the transition when the user commits back
                controller?.animateToEnd()
                isEnabled = false
            }

            override fun handleOnBackCancelled() {
                super.handleOnBackCancelled()
                // If the user cancels the back gesture, reset the state
                transition(ShowText.LONG)
            }
        }

        binding.shortText.setOnClickListener {
            transition(ShowText.LONG)
            callback.isEnabled = true
        }

        binding.longText.movementMethod = ScrollingMovementMethod()

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun changeTextVisibility(showText: ShowText) {
        when (showText) {
            ShowText.SHORT -> {
                binding.shortText.isVisible = true
                binding.longText.isVisible = false
            }

            ShowText.LONG -> {
                binding.shortText.isVisible = false
                binding.longText.isVisible = true
            }
        }
    }

    private fun transition(showText: ShowText) {
        TransitionManager.beginDelayedTransition(
            binding.root,
            transitionSet
        )
        changeTextVisibility(showText)
    }
}
