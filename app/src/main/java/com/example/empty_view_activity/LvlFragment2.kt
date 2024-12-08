package com.example.empty_view_activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.FileProvider
import com.example.empty_view_activity.databinding.FragmentLvl2Binding
import java.io.File
import java.io.FileOutputStream


class LvlFragment2 : Fragment() {
    private lateinit var binding: FragmentLvl2Binding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLvl2Binding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.taskCardView.setOnClickListener {
            binding.toShow.visibility = View.VISIBLE
            binding.toHide.visibility = View.GONE
        }
        binding.presentationButton.setOnClickListener{
            openPresentation()
        }

    }

    private fun openPresentation() {
        // Копируем файл из raw в кеш
        val inputStream = resources.openRawResource(R.raw.my_presentation)
        val tempFile = File(requireContext().cacheDir, "presentation.pptx")

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
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }
        intent.setType("application/vnd.openxmlformats-officedocument.presentationml.presentation");

        // Запускаем Intent с выбором приложения
        if (intent.resolveActivity(requireActivity().packageManager) != null) {
            startActivity(intent)
        } else {
            Toast.makeText(context, "Нет приложений для открытия презентации.", Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = LvlFragment2()
    }
}

