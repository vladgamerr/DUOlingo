package com.example.empty_view_activity

import FragmentNavigator
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import com.example.empty_view_activity.databinding.FragmentHomeBinding


class Home : Fragment() {
    private lateinit var fragmentNavigator: FragmentNavigator


    private val dataModel : DataModel by activityViewModels()
    lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding = FragmentHomeBinding.inflate(inflater)
        return binding.root    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
       // dataModel.messageForFragment1.observe(activity as LifecycleOwner,{
       //     binding.tvMessage1.text =it
        //})
        fragmentNavigator = FragmentNavigator(requireActivity() as AppCompatActivity)
        // Настройка обработки кнопки "Назад" через FragmentNavigator
        fragmentNavigator.setupBackNavigation(this)
        binding.btolvls.setOnClickListener{
            fragmentNavigator.openFragment(LvlChoose.newInstance(), R.id.place_holder1)
        }


        }
      //  binding.bMestoAcivity1.setOnClickListener{
       //     dataModel.messageForActivity.value = "Hello activity from Fragment 1"
        //}*/


    companion object {
        @JvmStatic
        fun newInstance() = Home()

            }

}