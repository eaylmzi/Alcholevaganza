package com.example.alcholevaganza

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.alcholevaganza.database.UserDatabase
import com.example.alcholevaganza.databinding.FragmentBeverageInfoBinding
import com.example.alcholevaganza.menu.MenuActivity
import com.google.android.material.snackbar.Snackbar


class BeverageInfoFragment : Fragment() {

    private var _binding: FragmentBeverageInfoBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        _binding = FragmentBeverageInfoBinding.inflate(inflater, container, false)
        val view = binding.root


        val application = requireNotNull(this.activity).application
        val dao = UserDatabase.getInstance(application).favouriteBeverage
        val viewModelFactory = FavouriteBeverageViewModelFactory(dao)
        val viewModel = ViewModelProvider(this, viewModelFactory)
            .get(FavouriteBeverageViewModel::class.java)




        val beverageId = BeverageInfoFragmentArgs.fromBundle(requireArguments()).beverageId
        val beverageName = BeverageInfoFragmentArgs.fromBundle(requireArguments()).beverageName

        binding.beverageName.text = beverageName.uppercase()
        val imageString =beverageName+beverageId.toString()


        val resourceId = resources.getIdentifier(imageString, "drawable", context?.packageName)
            binding.beverageImage.setImageResource(resourceId)
        val resourceId2 = resources.getIdentifier(imageString, "string",  context?.packageName)

        binding.explanation.text = resources.getString(resourceId2)

        binding.beverageBackButton.setOnClickListener {
            val action = BeverageInfoFragmentDirections.actionBeverageInfoFragmentToSearchFragment()
            this.findNavController().navigate(action)
        }


        binding.addFavouriteButtonBeverage.setOnClickListener{
            val number = MenuActivity.userNumber.toLong()
            viewModel.findBeverage(number,beverageId)
            if (viewModel.beverage?.beverageId?.equals(beverageId) == true &&
                viewModel.beverage?.userId?.equals(MenuActivity.userNumber.toLong()) == true   ) {
                Snackbar.make(it, "This beverage is also in list!!", Snackbar.LENGTH_LONG)
                    .show()
            }
            else{
                viewModel.addFavourite(MenuActivity.userNumber.toLong(), beverageId)
                Snackbar.make(it, "Added to your favourite !!", Snackbar.LENGTH_LONG)
                    .show()
            }


        }
        return view
    }


}