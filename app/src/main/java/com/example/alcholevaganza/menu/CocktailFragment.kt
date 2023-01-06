package com.example.alcholevaganza.menu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.alcholevaganza.*
import com.example.alcholevaganza.CocktailItemAdapter
import com.example.alcholevaganza.CocktailViewModel
import com.example.alcholevaganza.CocktailViewModelFactory
import com.example.alcholevaganza.database.UserDatabase
import com.example.alcholevaganza.databinding.FragmentCocktailBinding
import com.google.android.material.snackbar.Snackbar


class CocktailFragment : Fragment() {
    private var _binding: FragmentCocktailBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentCocktailBinding.inflate(inflater, container, false)
        val view = binding.root

        val application = requireNotNull(this.activity).application
        val dao = UserDatabase.getInstance(application).cocktailDao
        val viewModelFactory = CocktailViewModelFactory(dao)
        val viewModel = ViewModelProvider(this, viewModelFactory)
            .get(CocktailViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        val cocktailToolbar = binding.cocktailToolbar
        (activity as AppCompatActivity).setSupportActionBar(cocktailToolbar)


        val adapter = CocktailItemAdapter { cocktail ->
            viewModel.onTaskClickedCocktail(cocktail)
        }
        binding.cocktailList.adapter = adapter

        viewModel.cocktails.observe(viewLifecycleOwner, Observer{
            it?.let{
                adapter.submitList(it)
            }
        })


        viewModel.navigateToTaskCocktail.observe(viewLifecycleOwner, Observer { cocktail ->
            cocktail?.let{
                val action = CocktailFragmentDirections.actionCocktailFragmentToCocktailInfoFragment(cocktail.Id, cocktail.cocktailName,cocktail.cocktailIngredients)
                this.findNavController().navigate(action)
                viewModel.onTaskNavigatedCocktail()
            }
        })

        binding.cocktailButton.setOnClickListener {
            var ingredients = binding.searchFieldCocktail.text.toString()
            viewModel.getSpesific(ingredients)
            if (viewModel.cocktails.value!!.isEmpty()){
                ingredients = "%$ingredients%"
                viewModel.getCocktailsByName(ingredients)
                if (viewModel.cocktails.value!!.isEmpty()){
                    Snackbar.make(it, "The cocktail you are looking for is not on the list", Snackbar.LENGTH_LONG)
                        .show()
                }
            }

        }

       return view
    }


}