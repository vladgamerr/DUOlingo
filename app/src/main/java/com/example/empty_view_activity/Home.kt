package com.example.empty_view_activity

import FragmentNavigator
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
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

      /*  // Инициализация VideoView
        val videoView = binding.videoView2

        // Установка видео из ресурсов
        val videoUri: Uri = Uri.parse("android.resource://" + packageName + "/" + R.raw.your_video)

        // Установка контроллера
        val mediaController = MediaController(this)
        mediaController.setAnchorView(videoView)

        // Установка видео и контроллера
        videoView.setMediaController(mediaController)
        videoView.setVideoURI(videoUri)

        // Начало воспроизведения
        videoView.requestFocus()
        videoView.start()*/
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