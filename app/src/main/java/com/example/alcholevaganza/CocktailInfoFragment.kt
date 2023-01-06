package com.example.alcholevaganza

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.alcholevaganza.database.UserDatabase
import com.example.alcholevaganza.databinding.FragmentCocktailInfoBinding
import com.example.alcholevaganza.menu.MenuActivity.Companion.userNumber
import com.google.android.material.snackbar.Snackbar


class CocktailInfoFragment : Fragment() {
    private var _binding: FragmentCocktailInfoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        _binding = FragmentCocktailInfoBinding.inflate(inflater, container, false)
        val view = binding.root

        val application = requireNotNull(this.activity).application
        val dao = UserDatabase.getInstance(application).favouriteCocktail
        val viewModelFactory = FavouriteCocktailViewModelFactory(dao)
        val viewModel = ViewModelProvider(this, viewModelFactory)
            .get(FavouriteCocktailViewModel::class.java)


        val cocktailId = CocktailInfoFragmentArgs.fromBundle(requireArguments()).cocktailId
        val cocktailName = CocktailInfoFragmentArgs.fromBundle(requireArguments()).cocktailName
        val cocktailIngredients = CocktailInfoFragmentArgs.fromBundle(requireArguments()).cocktailIngredients

        binding.cocktailName.text = cocktailName.uppercase()
           val imageString =cocktailName+cocktailId.toString()

        val ingredients = cocktailIngredients.split(", ")
        val ingredientsLine = ingredients.joinToString("\n")


        val resourceId = resources.getIdentifier(imageString, "drawable", context?.packageName)
        binding.cocktailImage.setImageResource(resourceId)
        val resourceId2 = resources.getIdentifier(imageString, "string", context?.packageName)


        binding.cocktailIngredients.text = ingredientsLine
        binding.cocktailExplanation.text = resources.getString(resourceId2)

        binding.cocktailBackButton.setOnClickListener {
            val action = CocktailInfoFragmentDirections.actionCocktailInfoFragmentToCocktailFragment()
            this.findNavController().navigate(action)
        }
        binding.addFavouriteButton.setOnClickListener{
            val number = userNumber.toLong()
            viewModel.findCocktail(number,cocktailId)
            if (viewModel.cocktail?.cocktailId?.equals(cocktailId) == true &&
                viewModel.cocktail?.userId?.equals(userNumber.toLong()) == true   ) {
                Snackbar.make(it, "This cocktail is also in list!!", Snackbar.LENGTH_LONG)
                    .show()
            }
            else{
                viewModel.addFavourite(userNumber.toLong(), cocktailId)
                Snackbar.make(it, "Added to your favourite !!", Snackbar.LENGTH_LONG)
                    .show()
            }


        }


        return view
    }


}