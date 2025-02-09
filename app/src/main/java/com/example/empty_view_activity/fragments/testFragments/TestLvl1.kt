package com.example.empty_view_activity.fragments.testFragments

import FragmentNavigator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.empty_view_activity.R
import com.example.empty_view_activity.databinding.FragmentTestLvl1Binding

class TestLvl1 : Fragment() {
    private lateinit var binding: FragmentTestLvl1Binding
    private lateinit var fragmentNavigator: FragmentNavigator

    private val questions = arrayOf(
        "Choose the years of P.M. Masherov's life.",
        "Where was P.M. Masherov born?",
        "How many brothers and sisters did P.M. Masherov have?",
        "How many children did P.M. Masherov have?",
        "What faculty has P.M. Masherov graduated from while studying at Vitebsk Pedagogical Institute named after S.M. Kirov?",
        "Choose the main hobbies of P.M. Masherov (one or several answers):",
        "What objects are named after Pyotr Masherov in Belarus? Choose one or several answers.",
        "What awards and titles did P.M. Masherov have? Choose one or several answers.",
        "What title was given to the ‘P.M. Masherov Memorial Museum’ located at Vitebsk State University’?"
    )

    private val options = arrayOf(
        arrayOf("1918-1970", "1917-1980", "1918-1980", "1917-1970"), arrayOf(
            "Shirky, Senno district, Western region, RSFSR",
            "Senno, Western Oblast, RSFSR",
            "Vitebsk, Western Oblast, RSFSR",
            "Bogushevsk, Western Oblast, RSFSR"
        ), arrayOf(
            "One sister and three brothers",
            "One brother and three sisters",
            "Two brothers and two sisters",
            "Four sisters"
        ), arrayOf(
            "Two sons",
            "Two daughters",
            "One son and one daughter",
            "There were no children in the family"
        ), arrayOf(
            "Physics and Mathematics", "Chemistry and Biology", "Philology", "Pedagogical"
        ), arrayOf(
            "Ballet", "Reading", "Archery", "Water skiing"
        ), arrayOf(
            "One of the central avenues of Minsk",
            "Brest State Regional General Education Lyceum",
            "Hospital in Minsk",
            "Secondary school in Rossony"
        ), arrayOf(
            "Hero of the Soviet Union (1944)",
            "Hero of Socialist Labour (1978)",
            "Seven orders of Lenin",
            "Medal ‘Partisan of the Patriotic War’ of the I degree"
        ), arrayOf(
            "State", "People's", "Honoured", "Department"
        )
    )

    private val correctAnswers = arrayOf(
        arrayOf(3),
        arrayOf(1),
        arrayOf(2),
        arrayOf(2),
        arrayOf(1),
        arrayOf(1, 2),
        arrayOf(1, 2, 4),
        arrayOf(1, 2, 3, 4),
        arrayOf(2)
    )

    private var currentQuestionIndex = 0;
    private var score = 0;
    private val maxScore = 9;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTestLvl1Binding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentNavigator = FragmentNavigator(requireActivity() as AppCompatActivity)

        changeQuestion(currentQuestionIndex)

        binding.nextButton.setOnClickListener {
            checkAnswers()
            if (currentQuestionIndex < 8) {
                currentQuestionIndex++
                changeQuestion(currentQuestionIndex)
            } else {
                fragmentNavigator.openFragment(
                    ResultFragment.newInstance(score, maxScore),
                    R.id.place_holder1,true
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
        binding.textAnswer1.isChecked = false
        binding.textAnswer2.isChecked = false
        binding.textAnswer3.isChecked = false
        binding.textAnswer4.isChecked = false

        binding.textQuestion.setText(questions[index])
        binding.textAnswer1.setText(options[index][0])
        binding.textAnswer2.setText(options[index][1])
        binding.textAnswer3.setText(options[index][2])
        binding.textAnswer4.setText(options[index][3])
    }

    companion object {
        @JvmStatic
        fun newInstance() = TestLvl1()
    }
}