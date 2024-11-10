package com.example.empty_view_activity

import FragmentNavigator
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import com.example.empty_view_activity.databinding.FragmentLvlChooseBinding

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
            fragmentNavigator.openFragment(LvlFragment1.newInstance(), R.id.place_holder1)
        }
        binding.imageButton2.setOnClickListener {
            fragmentNavigator.openFragment(LvlFragment2.newInstance(), R.id.place_holder1)
        }
        binding.imageButton3.setOnClickListener {
            fragmentNavigator.openFragment(LvlFragment3.newInstance(), R.id.place_holder1)
        }
        binding.imageButton4.setOnClickListener {
            fragmentNavigator.openFragment(LvlFragment4.newInstance(), R.id.place_holder1)
        }
        binding.imageButton5.setOnClickListener {
            fragmentNavigator.openFragment(LvlFragment5.newInstance(), R.id.place_holder1)
        }
        binding.imageButton6.setOnClickListener {
            fragmentNavigator.openFragment(LvlFragment6.newInstance(), R.id.place_holder1)
        }
        binding.imageButton7.setOnClickListener {
            fragmentNavigator.openFragment(LvlFragment7.newInstance(), R.id.place_holder1)
        }
        binding.imageButton8.setOnClickListener {
            fragmentNavigator.openFragment(LvlFragment8.newInstance(), R.id.place_holder1)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = LvlChoose()
    }
}