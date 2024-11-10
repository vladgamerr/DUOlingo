package com.example.empty_view_activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment

class LvlFragment1 : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Добавление колбэка для обработки нажатия кнопки "Назад"
        requireActivity().onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
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
        return true // Логика для определения, нужно ли возвращаться
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_lvl1, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() = LvlFragment1()
    }
}