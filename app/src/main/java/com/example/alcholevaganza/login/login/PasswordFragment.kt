package com.example.alcholevaganza.login.login

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
import com.example.alcholevaganza.databinding.FragmentPasswordBinding
import com.google.android.material.snackbar.Snackbar


class PasswordFragment : Fragment() {
    private var _binding: FragmentPasswordBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        _binding = FragmentPasswordBinding.inflate(inflater, container, false)
        val view = binding.root

        val application = requireNotNull(this.activity).application
        val dao = UserDatabase.getInstance(application).userDao
        val viewModelFactory = UserViewModelFactory(dao)
        val viewModel = ViewModelProvider(this, viewModelFactory)
            .get(UserViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner


        binding.reset.setOnClickListener {
            viewModel.getUser()
            if (!(viewModel.user?.userEmail.isNullOrEmpty()) && viewModel.user?.userPassword.equals(binding.resetPasswordOldPasswordEdit.text.toString()) ) {
                viewModel.updateUserPassword(viewModel.user!!.userEmail)
                val action = PasswordFragmentDirections.actionPasswordFragmentToLoginFragment()
                view.findNavController().navigate(action)
                Snackbar.make(it, "Updated password successfully !!", Snackbar.LENGTH_LONG)
                    .show()
            } else {
                Snackbar.make(it, "User not found", Snackbar.LENGTH_LONG)
                    .show()
            }


        }
        return view
    }

}