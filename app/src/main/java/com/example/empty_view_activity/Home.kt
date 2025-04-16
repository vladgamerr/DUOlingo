package com.example.empty_view_activity

import FragmentNavigator
import android.media.MediaMetadataRetriever
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.empty_view_activity.databinding.FragmentHomeBinding

class Home : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val dataModel: DataModel by activityViewModels()
    private lateinit var fragmentNavigator: FragmentNavigator

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        setupVideoPlayer()
        return binding.root
    }

    private fun setupVideoPlayer() {
        val videoView = binding.videoView2
        val playButton = binding.playButton
        val thumbnail = binding.videoThumbnail

        val videoUri: Uri = Uri.parse("android.resource://${requireContext().packageName}/${R.raw.hello}")

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
        fragmentNavigator = FragmentNavigator(requireActivity() as AppCompatActivity)
        fragmentNavigator.setupBackNavigation(this)

        binding.btolvls.setOnClickListener {
            fragmentNavigator.openFragment(LvlChoose.newInstance(), R.id.place_holder1)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = Home()
    }
}
