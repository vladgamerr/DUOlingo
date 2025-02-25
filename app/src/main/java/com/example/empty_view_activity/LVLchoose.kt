package com.example.empty_view_activity

import FragmentNavigator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.empty_view_activity.databinding.FragmentLvlChooseBinding
import com.example.empty_view_activity.fragments.authFragments.CodeFragment

class LvlChoose : Fragment() {
    private lateinit var fragmentNavigator: FragmentNavigator
    private val dataModel: DataModel by activityViewModels()
    private lateinit var binding: FragmentLvlChooseBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLvlChooseBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragmentNavigator = FragmentNavigator(requireActivity() as AppCompatActivity)
        // Настройка обработки кнопки "Назад" через FragmentNavigator
        fragmentNavigator.setupBackNavigation(this)
        binding.imageButton1.setOnClickListener {
            fragmentNavigator.openFragment(CodeFragment.newInstance(1), R.id.place_holder1)
        }
        binding.imageButton2.setOnClickListener {
            fragmentNavigator.openFragment(CodeFragment.newInstance(2), R.id.place_holder1)
        }
        binding.imageButton3.setOnClickListener {
            fragmentNavigator.openFragment(CodeFragment.newInstance(3), R.id.place_holder1)
        }
        binding.imageButton4.setOnClickListener {
            fragmentNavigator.openFragment(CodeFragment.newInstance(4), R.id.place_holder1)
        }
        binding.imageButton5.setOnClickListener {
            fragmentNavigator.openFragment(CodeFragment.newInstance(5), R.id.place_holder1)
        }
        binding.imageButton6.setOnClickListener {
            fragmentNavigator.openFragment(CodeFragment.newInstance(6), R.id.place_holder1)
        }
        binding.imageButton7.setOnClickListener {
            fragmentNavigator.openFragment(CodeFragment.newInstance(7), R.id.place_holder1)
        }
        binding.imageButton8.setOnClickListener {
            fragmentNavigator.openFragment(CodeFragment.newInstance(8), R.id.place_holder1)
        }
        binding.backButton.setOnClickListener {
            fragmentNavigator.openFragment(Home.newInstance(), R.id.place_holder1, true)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = LvlChoose()
    }
}