package com.example.alcholevaganza

import com.example.alcholevaganza.database.FavouriteBeverage
import androidx.recyclerview.widget.DiffUtil

class FavouriteBeverageDiffItemCallback : DiffUtil.ItemCallback<FavouriteBeverage>() {
    override fun areItemsTheSame(oldItem: FavouriteBeverage, newItem: FavouriteBeverage)
            = (oldItem.Id == newItem.Id)

    override fun areContentsTheSame(oldItem: FavouriteBeverage, newItem: FavouriteBeverage)
            =(oldItem == newItem)


}