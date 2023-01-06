package com.example.alcholevaganza

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.alcholevaganza.database.UserDatabase
import com.example.alcholevaganza.databinding.FragmentFavouriteBevaregeInfoBinding
import com.example.alcholevaganza.menu.MenuActivity.Companion.userNumber
import com.google.android.material.snackbar.Snackbar


class FavouriteBevaregeInfoFragment : Fragment() {

    private var _binding: FragmentFavouriteBevaregeInfoBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        _binding = FragmentFavouriteBevaregeInfoBinding.inflate(inflater, container, false)
        val view = binding.root

        val application = requireNotNull(this.activity).application
        val dao = UserDatabase.getInstance(application).favouriteBeverage
        val viewModelFactory = FavouriteBeverageViewModelFactory(dao)
        val viewModel = ViewModelProvider(this, viewModelFactory)
            .get(FavouriteBeverageViewModel::class.java)



        val beverageId = FavouriteBevaregeInfoFragmentArgs.fromBundle(requireArguments()).beverageId
        val beverageName = FavouriteBevaregeInfoFragmentArgs.fromBundle(requireArguments()).beverageName

        binding.beverageName.text = beverageName.uppercase()
        val imageString =beverageName+beverageId.toString()


        val resourceId = resources.getIdentifier(imageString, "drawable", context?.packageName)
        binding.beverageImage.setImageResource(resourceId)
        val resourceId2 = resources.getIdentifier(imageString, "string",  context?.packageName)

        binding.explanation.text = resources.getString(resourceId2)

        binding.beverageBackButton.setOnClickListener {
            val action = FavouriteBevaregeInfoFragmentDirections.actionFavouriteBevaregeInfoFragmentToFavouriteBeverageFragment()
            this.findNavController().navigate(action)
        }
        binding.deleteFavouriteButtonBeverage.setOnClickListener{
            viewModel.deleteBeverage(userNumber.toLong(),beverageId)
            val action = FavouriteBevaregeInfoFragmentDirections.actionFavouriteBevaregeInfoFragmentToFavouriteBeverageFragment()
            this.findNavController().navigate(action)
            Snackbar.make(it, "You removed your favourite", Snackbar.LENGTH_LONG)
                .show()
        }



        return view
    }



}