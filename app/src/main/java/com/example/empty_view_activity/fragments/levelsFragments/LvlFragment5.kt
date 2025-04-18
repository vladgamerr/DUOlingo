package com.example.empty_view_activity.fragments.levelsFragments

import FragmentNavigator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.empty_view_activity.R
import com.example.empty_view_activity.databinding.FragmentLvl5Binding
import com.example.empty_view_activity.fragments.testFragments.TestLvl5


class LvlFragment5 : Fragment() {


    private lateinit var binding: FragmentLvl5Binding
    private lateinit var fragmentNavigator: FragmentNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLvl5Binding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentNavigator = FragmentNavigator(requireActivity() as AppCompatActivity)

        binding.quizButton.setOnClickListener {
            fragmentNavigator.openFragment(TestLvl5.newInstance(), R.id.place_holder1, true)
        }
        binding.backButton.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }

    companion object {

        @JvmStatic
        fun newInstance() = LvlFragment5()
    }
}
