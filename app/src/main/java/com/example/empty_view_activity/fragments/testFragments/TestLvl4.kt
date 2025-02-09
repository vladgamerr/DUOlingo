package com.example.empty_view_activity.fragments.testFragments

import FragmentNavigator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.empty_view_activity.R
import com.example.empty_view_activity.databinding.FragmentTestLvl4Binding

class TestLvl4 : Fragment() {
    private lateinit var binding: FragmentTestLvl4Binding
    private lateinit var fragmentNavigator: FragmentNavigator

    private val questions = arrayOf(
        R.drawable.testlvl4_pmmasherovmemorialmuseum,
        R.drawable.testlvl4_museumofthehistory,
        R.drawable.testlvl4_conferencehall,
        R.drawable.testlvl4_astronomicalobservatory,
    )

    private val options = arrayOf(
        arrayOf(
            "Museum of the History of the University",
            "P.M. Masherov Memorial Museum",
            "Conference hall",
            "Astronomical observatory"
        ), arrayOf(
            "Museum of the History of the University",
            "P.M. Masherov Memorial Museum",
            "Conference hall",
            "Astronomical observatory"
        ), arrayOf(
            "Conference hall",
            "Botanical Garden",
            "The Museum of the History of the University",
            "Astronomical observatory"
        ), arrayOf(
            "Conference hall",
            "Botanical Garden",
            "The Museum of the History of the University",
            "Astronomical observatory"
        )
    )

    private val correctAnswers = arrayOf(
        arrayOf(2),
        arrayOf(1),
        arrayOf(1),
        arrayOf(4),
    )

    private var currentQuestionIndex = 0;
    private var score = 0;
    private val maxScore = 4;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTestLvl4Binding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentNavigator = FragmentNavigator(requireActivity() as AppCompatActivity)

        changeQuestion(currentQuestionIndex)

        binding.nextButton.setOnClickListener {
            checkAnswers()
            if (currentQuestionIndex < questions.size-1) {
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
        fun newInstance() = TestLvl4()
    }
}