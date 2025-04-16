package com.example.empty_view_activity.fragments.levelsFragments

import FragmentNavigator
import android.media.MediaMetadataRetriever
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.empty_view_activity.R
import com.example.empty_view_activity.databinding.FragmentLvl1Binding
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
        setupVideoPlayer()
        return view
    }
    private fun setupVideoPlayer() {
        val videoView = binding.videoView
        val playButton = binding.playButton
        val thumbnail = binding.videoThumbnail

        val videoUri: Uri = Uri.parse("android.resource://${requireContext().packageName}/${R.raw.omasherove}")

        // Устанавливаем первый кадр видео в заглушку
        val retriever = MediaMetadataRetriever()
        retriever.setDataSource(requireContext(), videoUri)
        val bitmap = retriever.getFrameAtTime(1, MediaMetadataRetriever.OPTION_CLOSEST_SYNC)
        retriever.release()

        if (bitmap != null) {
            thumbnail.setImageBitmap(bitmap)
        }

        val mediaController = MediaController(requireContext())
        mediaController.setAnchorView(videoView)
        videoView.setMediaController(mediaController)

        // Прячем видео, пока пользователь не нажмет "Старт"
        thumbnail.visibility = View.VISIBLE
        videoView.visibility = View.GONE
        playButton.visibility = View.VISIBLE

        // Обработчик нажатия на кнопку "Старт"
        playButton.setOnClickListener {
            thumbnail.visibility = View.GONE
            playButton.visibility = View.GONE
            videoView.visibility = View.VISIBLE

            videoView.setVideoURI(videoUri)
            videoView.setOnPreparedListener {
                videoView.start()
            }
        }

        // Обработчик завершения видео
        videoView.setOnCompletionListener {
            playButton.visibility = View.VISIBLE
            thumbnail.visibility = View.VISIBLE
            videoView.visibility = View.GONE
        }
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