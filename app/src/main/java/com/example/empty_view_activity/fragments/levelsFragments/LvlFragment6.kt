package com.example.empty_view_activity.fragments.levelsFragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import com.example.empty_view_activity.R
import com.example.empty_view_activity.databinding.FragmentLvl2Binding
import com.example.empty_view_activity.databinding.FragmentLvl6Binding
import java.io.File
import java.io.FileOutputStream


class LvlFragment6 : Fragment() {
    private lateinit var binding: FragmentLvl6Binding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLvl6Binding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.taskCardView.setOnClickListener {
            binding.toShow.visibility = View.VISIBLE
            binding.toHide.visibility = View.GONE
        }
        binding.presentationButton.setOnClickListener {
            openPresentation()
        }
        binding.backButton.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

    }

    private fun openPresentation() {
        // Копируем файл из raw в кеш
        val inputStream = resources.openRawResource(R.raw.tourism)
        val tempFile = File(requireContext().cacheDir, "document.pdf")

        inputStream.use { input ->
            FileOutputStream(tempFile).use { output ->
                input.copyTo(output)
            }
        }
        // Генерируем URI с помощью FileProvider
        val uri: Uri = FileProvider.getUriForFile(
            requireContext(),
            "${requireContext().packageName}.provider",
            tempFile
        )

        // Создаем Intent для открытия файла
        val intent = Intent(Intent.ACTION_VIEW).apply {
            setDataAndType(uri, "application/pdf")
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }
        try {
            startActivity(
                Intent.createChooser(
                    intent,
                    "Выберите приложение для открытия презентации"
                )
            )
        } catch (e: Exception) {
            Toast.makeText(
                requireContext(),
                "Нет приложений для открытия презентации",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = LvlFragment6()
    }
}

