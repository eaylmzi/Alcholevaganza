package com.example.alcholevaganza

import com.example.alcholevaganza.database.Cocktail
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.alcholevaganza.databinding.CocktailItemBinding

class CocktailItemAdapter(val clickListener: (cocktail: Cocktail) -> Unit) :
    ListAdapter<Cocktail, CocktailItemAdapter.CocktailItemHolder>(CocktailDiffItemCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            : CocktailItemHolder = CocktailItemHolder.inflateFrom(parent)

    override fun onBindViewHolder(holder: CocktailItemHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, clickListener)
    }

    class CocktailItemHolder(val binding: CocktailItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun inflateFrom(parent: ViewGroup): CocktailItemHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = CocktailItemBinding.inflate(layoutInflater, parent, false)
                return CocktailItemHolder(binding)
            }
        }

        fun bind(item: Cocktail, clickListener: (cocktail: Cocktail) -> Unit) {
            binding.cocktailName.text = item.cocktailName
            binding.root.setOnClickListener {
                clickListener(item)
            }

        }
    }
}