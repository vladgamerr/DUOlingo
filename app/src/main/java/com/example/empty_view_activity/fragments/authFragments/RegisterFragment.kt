package com.example.empty_view_activity.fragments.authFragments

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
import com.example.empty_view_activity.Home
import com.example.empty_view_activity.R
import com.example.empty_view_activity.databinding.FragmentRegisterBinding
import com.google.firebase.auth.FirebaseAuth


class RegisterFragment : Fragment() {
    private lateinit var binding: FragmentRegisterBinding
    private lateinit var mAuth: FirebaseAuth
    private lateinit var fragmentNavigator: FragmentNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mAuth = FirebaseAuth.getInstance()
        fragmentNavigator = FragmentNavigator(requireActivity() as AppCompatActivity)

        binding.registerButton.setOnClickListener {
            binding.progressBar.visibility = View.VISIBLE
            var email = binding.phone.text.toString()
            var password = binding.password.text.toString()
            var confirmPassword = binding.confirmPassword.text.toString()
            if (TextUtils.isEmpty(email)) {
                Toast.makeText(context, "Enter email address", Toast.LENGTH_SHORT).show();
                binding.progressBar.visibility = View.GONE
                return@setOnClickListener
            }
            if (TextUtils.isEmpty(password)) {
                Toast.makeText(context, "Enter password", Toast.LENGTH_SHORT).show();
                binding.progressBar.visibility = View.GONE
                return@setOnClickListener
            }

            if (password.length < 6) {
                Toast.makeText(
                    context,
                    "Password must be at least 6 characters long",
                    Toast.LENGTH_SHORT
                ).show()
                binding.progressBar.visibility = View.GONE
                return@setOnClickListener
            }

            if (password != confirmPassword) {
                Toast.makeText(
                    context,
                    "Passwords don't match",
                    Toast.LENGTH_SHORT
                ).show()
                binding.progressBar.visibility = View.GONE
                return@setOnClickListener
            }

            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                if (it.isSuccessful) {
                    parentFragmentManager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
                    fragmentNavigator.openFragment(Home.newInstance(), R.id.place_holder1,true)
                } else {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(
                        context,
                        "The email address is already in use",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

        binding.loginNow.setOnClickListener {
            fragmentNavigator.openFragment(AuthFragment.newInstance(), R.id.place_holder1)
        }

    }

    companion object {
        @JvmStatic
        fun newInstance() = RegisterFragment()
    }
}