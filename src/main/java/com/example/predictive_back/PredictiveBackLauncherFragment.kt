package com.example.predictive_back

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.android_predictive_back.R
import com.example.android_predictive_back.databinding.FragmentPredictiveBackLauncherBinding

class PredictiveBackLauncherFragment : Fragment(R.layout.fragment_predictive_back_launcher) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentPredictiveBackLauncherBinding.bind(view)
        binding.viewTransition.setOnClickListener {
            it.findNavController().navigate(R.id.to_predictiveBackViewTransitionFragment)
        }
        binding.fragmentTransition.setOnClickListener {
            it.findNavController().navigate(R.id.to_predictiveBackNavigationTransitionFragment)
        }
    }
}
