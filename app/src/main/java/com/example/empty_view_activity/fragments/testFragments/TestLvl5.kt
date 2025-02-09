package com.example.empty_view_activity.fragments.testFragments

import FragmentNavigator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.empty_view_activity.R
import com.example.empty_view_activity.databinding.FragmentTestLvl5Binding

class TestLvl5 : Fragment() {
    private lateinit var binding: FragmentTestLvl5Binding
    private lateinit var fragmentNavigator: FragmentNavigator

    private val questions = arrayOf(
        R.drawable.testlvl5_palaceofthepuslovskysinkossovo,
        R.drawable.testlvl5_museumreservenesvizh,
        R.drawable.testlvl5_mircastleandparkcomplex,
        R.drawable.testlvl5_khatynmemorialcomplex,
        R.drawable.testlvl5_memorialcomplexbrestherofortress
    )

    private val options = arrayOf(
        arrayOf(
            "Nesvizh National Historical and Cultural Museum-Reserve",
            "Palace of the Puslovskys in Kossovo",
            "Mir Castle and Park Complex",
            "Khatyn memorial complex"
        ), arrayOf(
            "National Historical and Cultural Museum-Reserve ‘Nesvizh’",
            "Palace of the Puslovskys in Kossovo",
            "Mir Castle and Park Complex",
            "Khatyn Memorial Complex"
        ), arrayOf(
            "National Historical and Cultural Museum-Reserve ‘Nesvizh’",
            "Palace of the Puslovskys in Kossovo",
            "Mir Castle and Park Complex",
            "Khatyn Memorial Complex"
        ), arrayOf(
            "National Historical and Cultural Museum-Reserve ‘Nesvizh’",
            "Palace of the Puslovskys in Kossovo",
            "Mir Castle and Park Complex",
            "Khatyn Memorial Complex"
        ), arrayOf(
            "National Historical and Cultural Museum-Reserve ‘Nesvizh’",
            "Memorial complex ‘Brest Hero Fortress’",
            "Mir Castle and Park Complex",
            "Khatyn Memorial Complex"
        )
    )

    private val correctAnswers = arrayOf(
        arrayOf(2),
        arrayOf(1),
        arrayOf(3),
        arrayOf(4),
        arrayOf(2),
    )

    private var currentQuestionIndex = 0;
    private var score = 0;
    private val maxScore = 5;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTestLvl5Binding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentNavigator = FragmentNavigator(requireActivity() as AppCompatActivity)

        changeQuestion(currentQuestionIndex)

        binding.nextButton.setOnClickListener {
            checkAnswers()
            if (currentQuestionIndex < questions.size - 1) {
                currentQuestionIndex++
                changeQuestion(currentQuestionIndex)
            } else {
                fragmentNavigator.openFragment(
                    ResultFragment.newInstance(score, maxScore),
                    R.id.place_holder1, true
                )
            }
        }
    }

    private fun checkAnswers() {
        val selectedAnswers = mutableListOf<Int>()

        if (binding.textAnswer1.isChecked) selectedAnswers.add(1)
        if (binding.textAnswer2.isChecked) selectedAnswers.add(2)
        if (binding.textAnswer3.isChecked) selectedAnswers.add(3)
        if (binding.textAnswer4.isChecked) selectedAnswers.add(4)

        val correctAnswerSet = correctAnswers[currentQuestionIndex].toSet()
        val selectedAnswerSet = selectedAnswers.toSet()

        if (selectedAnswerSet == correctAnswerSet) {
            score++
        }
    }

    private fun changeQuestion(index: Int) {
        binding.radioGroup.clearCheck()

        binding.imageView.setImageResource(questions[index])
        binding.textAnswer1.setText(options[index][0])
        binding.textAnswer2.setText(options[index][1])
        binding.textAnswer3.setText(options[index][2])
        binding.textAnswer4.setText(options[index][3])
    }

    companion object {
        @JvmStatic
        fun newInstance() = TestLvl5()
    }
}