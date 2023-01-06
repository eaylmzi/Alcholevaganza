package com.example.alcholevaganza.login.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.alcholevaganza.UserViewModel
import com.example.alcholevaganza.UserViewModelFactory
import com.example.alcholevaganza.database.UserDatabase
import com.example.alcholevaganza.databinding.FragmentLoginBinding
import com.example.alcholevaganza.menu.MenuActivity
import com.google.android.material.snackbar.Snackbar


class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {


        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        val view = binding.root

        val application = requireNotNull(this.activity).application
        val dao = UserDatabase.getInstance(application).userDao
        val viewModelFactory = UserViewModelFactory(dao)
        val viewModel = ViewModelProvider(this, viewModelFactory)
            .get(UserViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        binding.login.setOnClickListener {
            viewModel.validateUser()
            if (viewModel.newUser.value?.userEmail.isNullOrEmpty()){
                Snackbar.make(it, "Your password or email not valid", Snackbar.LENGTH_LONG)
                    .show()
            }
            else{


                    viewModel.getUserId(viewModel.newUser.value!!.userEmail)
                    val usernumber = viewModel.userId.toString()
                    val intent = Intent(requireActivity().baseContext,
                        MenuActivity::class.java)
                    intent.putExtra("userId",usernumber)
                    requireActivity().startActivity(intent)


            }
        }


        binding.resetPassword.setOnClickListener{
            val action = LoginFragmentDirections.actionLoginFragmentToPasswordFragment()
                view.findNavController().navigate(action)
        }
        binding.register.setOnClickListener {
            val action = LoginFragmentDirections.actionLoginFragmentToSignUpFragment()
                view.findNavController().navigate(action)
        }


        return view
    }
}