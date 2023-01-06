package com.example.alcholevaganza

import com.example.alcholevaganza.database.FavouriteCocktail

import androidx.recyclerview.widget.DiffUtil

class FavouriteCocktailDiffItemCallback : DiffUtil.ItemCallback<FavouriteCocktail>() {
    override fun areItemsTheSame(oldItem: FavouriteCocktail, newItem: FavouriteCocktail)
            = (oldItem.Id == newItem.Id)

    override fun areContentsTheSame(oldItem: FavouriteCocktail, newItem: FavouriteCocktail)
            =(oldItem == newItem)


}
