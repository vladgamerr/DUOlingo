package com.example.empty_view_activity

import FragmentNavigator
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.empty_view_activity.databinding.FragmentAuthBinding
import com.google.firebase.auth.FirebaseAuth


class AuthFragment : Fragment() {
    private lateinit var binding: FragmentAuthBinding
    private lateinit var mAuth: FirebaseAuth
    private lateinit var fragmentNavigator: FragmentNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAuthBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mAuth = FirebaseAuth.getInstance()
        fragmentNavigator = FragmentNavigator(requireActivity() as AppCompatActivity)
        binding.skipButton.setOnClickListener{
            parentFragmentManager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
            fragmentNavigator.openFragment(Home.newInstance(), R.id.place_holder1,true)
        }
        binding.loginButton.setOnClickListener {
            binding.progressBar.visibility = View.VISIBLE
            var number = binding.phone.text.toString()
            var password = binding.password.text.toString()
            if (TextUtils.isEmpty(number)) {
                Toast.makeText(context, "Enter card number", Toast.LENGTH_SHORT).show();
                binding.progressBar.visibility = View.GONE
                return@setOnClickListener
            }
            if (TextUtils.isEmpty(password)) {
                Toast.makeText(context, "Enter password", Toast.LENGTH_SHORT).show();
                binding.progressBar.visibility = View.GONE
                return@setOnClickListener
            }

            val email = "$number@example.com"
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                if (it.isSuccessful) {
                    parentFragmentManager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
                    fragmentNavigator.openFragment(Home.newInstance(), R.id.place_holder1,true)
                } else {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(context, "Authentication failed", Toast.LENGTH_SHORT).show();
                }
            }
        }

        binding.registerNow.setOnClickListener {
            fragmentNavigator.openFragment(RegisterFragment.newInstance(), R.id.place_holder1)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = AuthFragment()
    }
}