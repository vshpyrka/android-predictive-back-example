package com.example.predictive_back

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.android_predictive_back.R
import com.example.android_predictive_back.databinding.FragmentPredictiveBackNavigationTransitionOneBinding

class PredictiveBackNavigationTransitionFragmentOne : Fragment(
    R.layout.fragment_predictive_back_navigation_transition_one
) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentPredictiveBackNavigationTransitionOneBinding.bind(view)
        binding.button.setOnClickListener {
            it.findNavController().navigate(R.id.to_predictiveBackNavigationTransitionFragmentTwo)
        }
    }
}
