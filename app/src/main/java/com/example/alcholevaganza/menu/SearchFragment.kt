package com.example.alcholevaganza.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.alcholevaganza.BeverageItemAdapter
import com.example.alcholevaganza.BeverageViewModel
import com.example.alcholevaganza.BeverageViewModelFactory
import com.example.alcholevaganza.database.UserDatabase
import com.example.alcholevaganza.databinding.FragmentSearchBinding


class SearchFragment : Fragment() {
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        val view = binding.root


        val application = requireNotNull(this.activity).application
        val dao = UserDatabase.getInstance(application).beverageDao
        val viewModelFactory = BeverageViewModelFactory(dao)
        val viewModel = ViewModelProvider(this, viewModelFactory)
            .get(BeverageViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        val searchToolbar = binding.searchToolbar
        (activity as AppCompatActivity).setSupportActionBar(searchToolbar)

        val adapter = BeverageItemAdapter { beverage ->
            viewModel.onTaskClickedBeverage(beverage)
        }
        binding.beverageList.adapter = adapter

        viewModel.beverages.observe(viewLifecycleOwner, Observer{
            it?.let{
                adapter.submitList(it)
            }
        })


        viewModel.navigateToTaskBeverage.observe(viewLifecycleOwner, Observer { beverage ->
            beverage?.let{
                val action = SearchFragmentDirections.actionSearchFragmentToBeverageInfoFragment(beverage.Id, beverage.beverageName)
                this.findNavController().navigate(action)
                viewModel.onTaskNavigatedBeverage()
            }
        })


        binding.button.setOnClickListener {

        }
        return view
    }



}