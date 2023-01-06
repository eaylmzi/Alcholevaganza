package com.example.alcholevaganza

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.alcholevaganza.database.FavouriteCocktail
import com.example.alcholevaganza.databinding.FavouritecocktailItemBinding

class FavouriteCocktailItemAdapter(val clickListener: (favouriteCocktail: FavouriteCocktail) -> Unit) :
    ListAdapter<FavouriteCocktail, FavouriteCocktailItemAdapter.FavouriteCocktailItemHolder>(
        FavouriteCocktailDiffItemCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            : FavouriteCocktailItemHolder = FavouriteCocktailItemHolder.inflateFrom(parent)

    override fun onBindViewHolder(holder: FavouriteCocktailItemHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, clickListener)
    }

    class FavouriteCocktailItemHolder(val binding: FavouritecocktailItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun inflateFrom(parent: ViewGroup): FavouriteCocktailItemHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = FavouritecocktailItemBinding.inflate(layoutInflater, parent, false)
                return FavouriteCocktailItemHolder(binding)
            }
        }

        fun bind(item: FavouriteCocktail, clickListener: (favouriteCocktail: FavouriteCocktail) -> Unit) {
            binding.favouritecocktailName.text = item.cocktailId.toString()
            binding.root.setOnClickListener {
                clickListener(item)
            }

        }
    }
}