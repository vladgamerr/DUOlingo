package com.example.empty_view_activity

import FragmentNavigator
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.empty_view_activity.databinding.ActivityMainBinding
import com.google.android.material.internal.EdgeToEdgeUtils

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val dataModel: DataModel by viewModels()
    lateinit var fragmentNavigator: FragmentNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // Инициализация FragmentNavigator
        fragmentNavigator = FragmentNavigator(this)

        // Открытие начального фрагмента
        openFragment(AuthFragment.newInstance(), R.id.place_holder1)
    }

    private fun openFragment(f: Fragment, idHolder: Int) {
            supportFragmentManager
            .beginTransaction()
            .replace(idHolder, f)
            //.addToBackStack(null)
            .commit()
        //fragmentNavigator.setupBackNavigation(f)
        //fragmentNavigator.openFragment(f, idHolder)
        // Настройка навигации "Назад" для нового фрагмента

    }
}