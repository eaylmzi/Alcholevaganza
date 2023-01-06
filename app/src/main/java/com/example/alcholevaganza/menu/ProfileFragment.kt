package com.example.alcholevaganza.menu

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.alcholevaganza.UserViewModel
import com.example.alcholevaganza.UserViewModelFactory

import com.example.alcholevaganza.database.UserDatabase
import com.example.alcholevaganza.databinding.FragmentProfileBinding
import com.example.alcholevaganza.LoginActivity
import com.example.alcholevaganza.menu.MenuActivity.Companion.userNumber
import com.google.android.material.snackbar.Snackbar


class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val view = binding.root

        val application = requireNotNull(this.activity).application
        val dao = UserDatabase.getInstance(application).userDao
        val viewModelFactory = UserViewModelFactory(dao)
        val viewModel = ViewModelProvider(this, viewModelFactory)
            .get(UserViewModel::class.java)



        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        val profileToolbar = binding.profileToolbar
        (activity as AppCompatActivity).setSupportActionBar(profileToolbar)


        viewModel.getUserWithId(userNumber.toLong())
        binding.userNameEditProfile.setText(viewModel.newUser.value!!.userName)
        binding.userSurnameEditProfile.setText(viewModel.newUser.value!!.userSurname)
        binding.userEmailEditProfile.setText(viewModel.newUser.value!!.userEmail)


        binding.updateButton.setOnClickListener {
            viewModel.updateUserName(binding.userNameEditProfile.text.toString(), userNumber.toLong())
            viewModel.updateUserSurname( binding.userSurnameEditProfile.text.toString(), userNumber.toLong())
            viewModel.updateUserEmail(binding.userEmailEditProfile.text.toString(), userNumber.toLong())
            Snackbar.make(it, "Successfully updated !!", Snackbar.LENGTH_LONG)
                .show()
        }

        binding.beverageFavourite.setOnClickListener {
            val action = ProfileFragmentDirections.actionProfileFragmentToFavouriteBeverageFragment()
            this.findNavController().navigate(action)
        }

        binding.cocktailFavourite.setOnClickListener {
            val action = ProfileFragmentDirections.actionProfileFragmentToFavouriteCocktailFragment()
            this.findNavController().navigate(action)
        }




        binding.deleteAccountButton.setOnClickListener {
            viewModel.deleteUser(viewModel.newUser.value!!.Id)
            switchActivities()
        }
        binding.logoutAccountButton.setOnClickListener {
            userNumber = "0"
            switchActivities()
        }


        return view
    }

    override fun onStart() {
        val application = requireNotNull(this.activity).application
        val dao = UserDatabase.getInstance(application).userDao
        val viewModelFactory = UserViewModelFactory(dao)
        val viewModel = ViewModelProvider(this, viewModelFactory)
            .get(UserViewModel::class.java)
        viewModel.getUserWithId(userNumber.toLong())
        binding.userNameEditProfile.setText(viewModel.newUser.value!!.userName)
        binding.userSurnameEditProfile.setText(viewModel.newUser.value!!.userSurname)
        binding.userEmailEditProfile.setText(viewModel.newUser.value!!.userEmail)
        super.onStart()
    }
    private fun switchActivities() {
        val switchActivityIntent = Intent(context, LoginActivity::class.java)
        startActivity(switchActivityIntent)
    }

}