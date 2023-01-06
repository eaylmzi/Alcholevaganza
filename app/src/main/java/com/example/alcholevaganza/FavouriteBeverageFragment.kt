package com.example.alcholevaganza

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.alcholevaganza.database.UserDatabase
import com.example.alcholevaganza.databinding.FragmentFavouriteBeverageBinding
import com.example.alcholevaganza.menu.MenuActivity.Companion.userNumber


class FavouriteBeverageFragment : Fragment() {
    private var _binding: FragmentFavouriteBeverageBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentFavouriteBeverageBinding.inflate(inflater, container, false)
        val view = binding.root


        val application = requireNotNull(this.activity).application
        val dao = UserDatabase.getInstance(application).favouriteBeverage
        val viewModelFactory = FavouriteBeverageViewModelFactory(dao)
        val viewModel = ViewModelProvider(this, viewModelFactory)
            .get(FavouriteBeverageViewModel::class.java)


        val applicationBeverage = requireNotNull(this.activity).application
        val daoBeverage = UserDatabase.getInstance(applicationBeverage).beverageDao
        val viewModelFactoryBeverage = BeverageViewModelFactory(daoBeverage)
        val viewModelBeverage = ViewModelProvider(this, viewModelFactoryBeverage)
            .get(BeverageViewModel::class.java)

        viewModel.getAll(userNumber.toLong())
        val beverageList = viewModel.favouriteBeverages.value

        if (beverageList != null) {
            for (item in beverageList){
                viewModelBeverage.getSpesificWithParameter(item.beverageId)
                viewModelBeverage.addItemToBeveragesList(item.beverageId)
            }
        }
        val adapter = BeverageItemAdapter { beverage ->
            viewModelBeverage.onTaskClickedBeverage(beverage)
        }
        binding.favouriteBeverageList.adapter = adapter

        viewModelBeverage.beverages.observe(viewLifecycleOwner, Observer{
            it?.let{
                adapter.submitList(it)
            }
        })

        viewModelBeverage.navigateToTaskBeverage.observe(viewLifecycleOwner, Observer { beverage ->
            beverage?.let{
                val action = FavouriteBeverageFragmentDirections.actionFavouriteBeverageFragmentToFavouriteBevaregeInfoFragment(beverage.Id, beverage.beverageName)
                this.findNavController().navigate(action)
                viewModelBeverage.onTaskNavigatedBeverage()
            }
        })
        binding.backButtonToProfile.setOnClickListener {
            val action = FavouriteBeverageFragmentDirections.actionFavouriteBeverageFragmentToProfileFragment(userNumber.toLong())
            this.findNavController().navigate(action)
        }



        return view
    }


}