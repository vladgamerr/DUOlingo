package com.example.empty_view_activity.fragments.testFragments

import FragmentNavigator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.empty_view_activity.LvlChoose
import com.example.empty_view_activity.R
import com.example.empty_view_activity.databinding.FragmentResultBinding
import com.example.empty_view_activity.databinding.FragmentTestLvl1Binding
import com.example.empty_view_activity.fragments.authFragments.RegisterFragment


class ResultFragment : Fragment() {
    private lateinit var binding: FragmentResultBinding
    private lateinit var fragmentNavigator: FragmentNavigator

    //param 1 - score
    //param 2 - max score

    private var param1: Int? = null
    private var param2: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getInt(ARG_PARAM1)
            param2 = it.getInt(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentResultBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentNavigator = FragmentNavigator(requireActivity() as AppCompatActivity)

        val score = arguments?.getInt(ARG_PARAM1) ?: 0
        val maxScore = arguments?.getInt(ARG_PARAM2) ?: 0

        binding.scoreText.text = "$score / $maxScore"

        binding.homeButton.setOnClickListener{
            fragmentNavigator.openFragment(LvlChoose.newInstance(), R.id.place_holder1,true)
        }
    }


    companion object {
        private const val ARG_PARAM1 = "param1"
        private const val ARG_PARAM2 = "param2"

        @JvmStatic
        fun newInstance(param1: Int, param2: Int) =
            ResultFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_PARAM1, param1)
                    putInt(ARG_PARAM2, param2)
                }
            }
    }
}