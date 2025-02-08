package com.example.empty_view_activity.fragments.levelsFragments

import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.fragment.app.Fragment
import com.example.empty_view_activity.R
import com.example.empty_view_activity.databinding.FragmentLvl8Binding

class LvlFragment8 : Fragment() {

    private var mediaPlayer: MediaPlayer? = null
    private var isPlaying = false
    private var _binding: FragmentLvl8Binding? = null
    private val binding get() = _binding!!
    private val handler = Handler()
    private var runnable: Runnable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLvl8Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mediaPlayer =
            MediaPlayer.create(context, R.raw.nationalcaracter) // Замените на имя вашего файла

        binding.playPauseButton.setOnClickListener {
            if (isPlaying) {
                pauseAudio()
            } else {
                playAudio()
            }
        }

        // Инициализация SeekBar
        mediaPlayer?.setOnPreparedListener {
            binding.seekBar.max = it.duration
            binding.totalTime.text = formatTime(it.duration)
            startUpdatingSeekBar()
        }

        binding.seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                mediaPlayer?.seekTo(seekBar.progress)
            }

            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {}
        })

        binding.taskCardView.setOnClickListener {
            binding.toShow.visibility = View.VISIBLE
            binding.toHide.visibility = View.GONE
        }

        binding.backButton.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }

    private fun playAudio() {
        mediaPlayer?.start()
        isPlaying = true
        binding.playPauseButton.setImageResource(R.drawable.ic_pause_foreground) // Иконка паузы
        startUpdatingSeekBar() // Запустить обновление SeekBar
    }

    private fun pauseAudio() {
        mediaPlayer?.pause()
        isPlaying = false
        binding.playPauseButton.setImageResource(R.drawable.ic_play_foreground) // Иконка воспроизведения
    }

    private fun startUpdatingSeekBar() {
        runnable = object : Runnable {
            override fun run() {
                mediaPlayer?.let {
                    binding.seekBar.progress = it.currentPosition
                    binding.currentTime.text = formatTime(it.currentPosition)
                }
                handler.postDelayed(this, 1000) // Обновление каждую секунду
            }
        }
        handler.post(runnable!!)
    }

    private fun formatTime(milliseconds: Int): String {
        val seconds = (milliseconds / 1000) % 60
        val minutes = (milliseconds / (1000 * 60)) % 60
        return String.format("%d:%02d", minutes, seconds)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        handler.removeCallbacks(runnable!!)
        _binding = null
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer?.release()
        mediaPlayer = null
    }

    companion object {
        @JvmStatic
        fun newInstance() = LvlFragment8()
    }
}