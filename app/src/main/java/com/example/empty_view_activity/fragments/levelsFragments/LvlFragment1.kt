package com.example.empty_view_activity.fragments.levelsFragments

import FragmentNavigator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.empty_view_activity.R
import com.example.empty_view_activity.databinding.FragmentLvl1Binding
import com.example.empty_view_activity.databinding.FragmentLvl2Binding
import com.example.empty_view_activity.fragments.authFragments.RegisterFragment
import com.example.empty_view_activity.fragments.testFragments.TestLvl1

class LvlFragment1 : Fragment() {
    private lateinit var binding: FragmentLvl1Binding
    private lateinit var fragmentNavigator: FragmentNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(
            this,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    if (shouldNavigateBack()) {
                        parentFragmentManager.popBackStack() // Возвращаемся к предыдущему фрагменту
                    } else {
                        // Если нужно, вы можете сделать что-то другое
                        isEnabled = false // Отключаем колбэк, если не нужно обрабатывать
                        requireActivity().onBackPressed() // Стандартное поведение
                    }
                }
            })
    }

    private fun shouldNavigateBack(): Boolean {
        return true
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLvl1Binding.inflate(inflater, container, false)
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
        binding.quizButton.setOnClickListener{
            fragmentNavigator.openFragment(TestLvl1.newInstance(), R.id.place_holder1,true)
        }
        binding.backButton.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = LvlFragment1()
    }
}