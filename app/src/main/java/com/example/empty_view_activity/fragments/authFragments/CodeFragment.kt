package com.example.empty_view_activity.fragments.authFragments

import FragmentNavigator
import android.os.Bundle
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.empty_view_activity.R
import com.example.empty_view_activity.databinding.FragmentCodeBinding
import com.example.empty_view_activity.fragments.levelsFragments.LvlFragment1
import com.example.empty_view_activity.fragments.levelsFragments.LvlFragment2
import com.example.empty_view_activity.fragments.levelsFragments.LvlFragment3
import com.example.empty_view_activity.fragments.levelsFragments.LvlFragment4
import com.example.empty_view_activity.fragments.levelsFragments.LvlFragment5
import com.example.empty_view_activity.fragments.levelsFragments.LvlFragment6
import com.example.empty_view_activity.fragments.levelsFragments.LvlFragment7
import com.example.empty_view_activity.fragments.levelsFragments.LvlFragment8
import java.util.Locale

private const val ARG_PARAM1 = "param1"

class CodeFragment : Fragment() {
    private lateinit var binding: FragmentCodeBinding
    private var param1: Int? = null
    private lateinit var fragmentNavigator: FragmentNavigator
    private var codes = mapOf(
        1 to "A2B1",
        2 to "L5U8",
        3 to "X9Y7",
        4 to "M4N2",
        5 to "Q3P6",
        6 to "Z8T5",
        7 to "W1V4",
        8 to "R6D9"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getInt(ARG_PARAM1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCodeBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentNavigator = FragmentNavigator(requireActivity() as AppCompatActivity)
        binding.verifyButton.setOnClickListener {
            val c1 = binding.code1.text.toString()
            val c2 = binding.code2.text.toString()
            val c3 = binding.code3.text.toString()
            val c4 = binding.code4.text.toString()
            val code = c1 + c2 + c3 + c4
            checkAndNavigate(param1!!, code)
        }

        binding.code1.filters = arrayOf(InputFilter.AllCaps(), InputFilter.LengthFilter(1))
        binding.code1.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (s.isNullOrEmpty()) return
                val upperCaseText = s.toString().uppercase(Locale.getDefault())
                if (s.toString() != upperCaseText) {
                    binding.code1.setText(upperCaseText)
                    binding.code1.setSelection(upperCaseText.length)
                }
                if (s?.length == 1) {
                    binding.code2.requestFocus()
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        binding.code2.filters = arrayOf(InputFilter.AllCaps(), InputFilter.LengthFilter(1))
        binding.code2.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (s.isNullOrEmpty()) return
                val upperCaseText = s.toString().uppercase(Locale.getDefault())
                if (s.toString() != upperCaseText) {
                    binding.code2.setText(upperCaseText)
                    binding.code2.setSelection(upperCaseText.length)
                }
                if (s?.length == 1) {
                    binding.code3.requestFocus()
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        binding.code3.filters = arrayOf(InputFilter.AllCaps(), InputFilter.LengthFilter(1))
        binding.code3.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (s.isNullOrEmpty()) return
                val upperCaseText = s.toString().uppercase(Locale.getDefault())
                if (s.toString() != upperCaseText) {
                    binding.code3.setText(upperCaseText)
                    binding.code3.setSelection(upperCaseText.length)
                }
                if (s?.length == 1) {
                    binding.code4.requestFocus()
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        binding.code4.filters = arrayOf(InputFilter.AllCaps(), InputFilter.LengthFilter(1))
        binding.code4.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (s.isNullOrEmpty()) return
                val upperCaseText = s.toString().uppercase(Locale.getDefault())
                if (s.toString() != upperCaseText) {
                    binding.code4.setText(upperCaseText)
                    binding.code4.setSelection(upperCaseText.length)
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }

    private fun checkAndNavigate(lvlNum: Int, code: String) {
        if (codes[lvlNum] == code) {
            fragmentNavigator.openFragment(getFragmentByLevel(lvlNum), R.id.place_holder1, true)
        } else {
            binding.invalidCodeText.visibility = View.VISIBLE
        }
    }

    private fun getFragmentByLevel(lvlNum: Int): Fragment {
        return when (lvlNum) {
            1 -> LvlFragment1()
            2 -> LvlFragment2()
            3 -> LvlFragment3()
            4 -> LvlFragment4()
            5 -> LvlFragment5()
            6 -> LvlFragment6()
            7 -> LvlFragment7()
            8 -> LvlFragment8()
            else -> throw IllegalArgumentException("Неизвестный уровень: $lvlNum")
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: Int) =
            CodeFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_PARAM1, param1)
                }
            }
    }
}