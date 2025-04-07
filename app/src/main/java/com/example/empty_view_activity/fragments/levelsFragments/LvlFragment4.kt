package com.example.empty_view_activity.fragments.levelsFragments

import FragmentNavigator
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.empty_view_activity.R
import com.example.empty_view_activity.databinding.FragmentLvl4Binding
import com.example.empty_view_activity.fragments.testFragments.TestLvl1
import com.example.empty_view_activity.fragments.testFragments.TestLvl4


class LvlFragment4 : Fragment() {
    private lateinit var binding: FragmentLvl4Binding
    private lateinit var fragmentNavigator: FragmentNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLvl4Binding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentNavigator = FragmentNavigator(requireActivity() as AppCompatActivity)

        binding.taskCardView.setOnClickListener {
            binding.toShow.visibility = View.VISIBLE
            binding.toHide.visibility = View.GONE
        }
        binding.quizButton.setOnClickListener {
            fragmentNavigator.openFragment(TestLvl4.newInstance(), R.id.place_holder1, true)
        }
        binding.backButton.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
        binding.linkButton.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://3d.vsu.by/"));
            startActivity(browserIntent);
        }
    }

    companion object {

        @JvmStatic
        fun newInstance() = LvlFragment4()
    }
}
