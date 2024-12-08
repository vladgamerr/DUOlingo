package com.example.empty_view_activity

import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import com.example.empty_view_activity.databinding.FragmentLvl7Binding

class LvlFragment7 : Fragment() {

    private var mediaPlayer: MediaPlayer? = null
    private var isPlaying = false
    private var _binding: FragmentLvl7Binding? = null
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
        _binding = FragmentLvl7Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mediaPlayer = MediaPlayer.create(context, R.raw.lvl7) // Замените на имя вашего файла

        binding.playPauseButton.setOnClickListener {
            if (isPlaying) {
                pauseAudio()
            } else {
                playAudio()
            }
        }

        binding.stopButton.setOnClickListener {
            stopAudio()
        }

        // Инициализация SeekBar
        binding.seekBar.max = mediaPlayer?.duration ?: 0
        mediaPlayer?.setOnPreparedListener {
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
    }

    private fun playAudio() {
        mediaPlayer?.start()
        isPlaying = true
        binding.playPauseButton.setImageResource(R.drawable.ic_pause_foreground) // Иконка паузы
    }

    private fun pauseAudio() {
        mediaPlayer?.pause()
        isPlaying = false
        binding.playPauseButton.setImageResource(R.drawable.ic_play_foreground) // Иконка воспроизведения
    }

    private fun stopAudio() {
        mediaPlayer?.stop()
        mediaPlayer?.prepareAsync() // Подготовка для последующего воспроизведения
        isPlaying = false
        binding.playPauseButton.setImageResource(R.drawable.ic_play_foreground) // Иконка воспроизведения
        binding.seekBar.progress = 0
        binding.currentTime.text = "0:00" // Сброс текущего времени
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
        fun newInstance() = LvlFragment7()
    }
}