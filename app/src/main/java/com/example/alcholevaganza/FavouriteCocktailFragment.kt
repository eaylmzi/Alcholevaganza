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
import com.example.alcholevaganza.databinding.FragmentFavouriteCocktailBinding
import com.example.alcholevaganza.menu.MenuActivity.Companion.userNumber


class FavouriteCocktailFragment : Fragment() {
    private var _binding: FragmentFavouriteCocktailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentFavouriteCocktailBinding.inflate(inflater, container, false)
        val view = binding.root


        val application = requireNotNull(this.activity).application
        val dao = UserDatabase.getInstance(application).favouriteCocktail
        val viewModelFactory = FavouriteCocktailViewModelFactory(dao)
        val viewModel = ViewModelProvider(this, viewModelFactory)
            .get(FavouriteCocktailViewModel::class.java)



        val applicationCocktail = requireNotNull(this.activity).application
        val daoBeverage = UserDatabase.getInstance(applicationCocktail).cocktailDao
        val viewModelFactoryCocktail = CocktailViewModelFactory(daoBeverage)
        val viewModelCocktail = ViewModelProvider(this, viewModelFactoryCocktail)
            .get(CocktailViewModel::class.java)

        viewModel.getAll(userNumber.toLong())
        val cocktailList = viewModel.favouriteCocktail.value

        if (cocktailList != null) {
            for (item in cocktailList){
                if (item != null) {
                    viewModelCocktail.getSpesificWithParameter(item.cocktailId)
                    viewModelCocktail.addItemToCocktailList(item.cocktailId)
                }

            }
        }
        val adapter = CocktailItemAdapter { cocktail ->
            viewModelCocktail.onTaskClickedCocktail(cocktail)
        }
        binding.favouriteCocktailList.adapter = adapter

        viewModelCocktail.cocktails.observe(viewLifecycleOwner, Observer{
            it?.let{
                adapter.submitList(it)
            }
        })

        viewModelCocktail.navigateToTaskCocktail.observe(viewLifecycleOwner, Observer { cocktail ->
            cocktail?.let{
                val action = FavouriteCocktailFragmentDirections.actionFavouriteCocktailFragmentToFavouriteCocktailInfoFragment(cocktail.Id, cocktail.cocktailName,cocktail.cocktailIngredients)
                this.findNavController().navigate(action)
                viewModelCocktail.onTaskNavigatedCocktail()
            }
        })

        binding.backButtonToProfile.setOnClickListener {
            val action = FavouriteCocktailFragmentDirections.actionFavouriteCocktailFragmentToProfileFragment(userNumber.toLong())
            this.findNavController().navigate(action)
        }
        return view
    }

}