package com.example.alcholevaganza.login.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.alcholevaganza.UserItemAdapter
import com.example.alcholevaganza.UserViewModel
import com.example.alcholevaganza.UserViewModelFactory
import com.example.alcholevaganza.database.UserDatabase
import com.example.alcholevaganza.databinding.FragmentSignUpBinding
import com.example.alcholevaganza.menu.MenuActivity
import com.google.android.material.snackbar.Snackbar


class SignUpFragment : Fragment() {
    private var _binding: FragmentSignUpBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        _binding = FragmentSignUpBinding.inflate(inflater, container, false)
        val view = binding.root
        val application = requireNotNull(this.activity).application
        val dao = UserDatabase.getInstance(application).userDao
        val viewModelFactory = UserViewModelFactory(dao)
        val viewModel = ViewModelProvider(this, viewModelFactory)
            .get(UserViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        binding.signUp.setOnClickListener {
            if (binding.agreement.isChecked) {
                viewModel.getUser()
                if (viewModel.user?.userEmail.isNullOrEmpty()) {
                    viewModel.addUser()
                    val action = SignUpFragmentDirections.actionSignUpFragmentToLoginFragment()
                    view.findNavController().navigate(action)
                    Snackbar.make(it, "Successfully created!!", Snackbar.LENGTH_LONG)
                        .show()
                } else {
                    Snackbar.make(it, "The email has been already added", Snackbar.LENGTH_LONG)
                        .show()
                }
            } else {
                Snackbar.make(it, "Please read the agreement and confirm", Snackbar.LENGTH_LONG)
                    .show()
            }

        }

        binding.back.setOnClickListener {
            val action = SignUpFragmentDirections.actionSignUpFragmentToLoginFragment()
            view.findNavController().navigate(action)
        }
        return view

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun switchActivities() {
        val switchActivityIntent = Intent(context, MenuActivity::class.java)
        startActivity(switchActivityIntent)
    }

    private fun isValidPassword(password: String): Boolean {
        if (password.length < 8) return false
        if (password.filter { it.isDigit() }.firstOrNull() == null) return false
        if (password.filter { it.isLetter() }.filter { it.isUpperCase() }
                .firstOrNull() == null) return false
        if (password.filter { it.isLetter() }.filter { it.isLowerCase() }
                .firstOrNull() == null) return false
        if (password.filter { !it.isLetterOrDigit() }.firstOrNull() == null) return false

        return true
    }

}