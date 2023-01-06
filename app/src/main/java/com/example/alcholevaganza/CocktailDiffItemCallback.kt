package com.example.alcholevaganza
import androidx.recyclerview.widget.DiffUtil
import com.example.alcholevaganza.database.Cocktail


class CocktailDiffItemCallback : DiffUtil.ItemCallback<Cocktail>() {
    override fun areItemsTheSame(oldItem: Cocktail, newItem: Cocktail)
            = (oldItem.Id == newItem.Id)

    override fun areContentsTheSame(oldItem: Cocktail, newItem: Cocktail)
            =(oldItem == newItem)


}