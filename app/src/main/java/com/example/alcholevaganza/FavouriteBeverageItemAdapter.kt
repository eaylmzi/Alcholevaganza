package com.example.alcholevaganza

import com.example.alcholevaganza.database.FavouriteBeverage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.alcholevaganza.databinding.FavouritebeverageItemBinding

class FavouriteBeverageItemAdapter(val clickListener: (favouriteBeverage: FavouriteBeverage) -> Unit) :
    ListAdapter<FavouriteBeverage, FavouriteBeverageItemAdapter.FavouriteBeverageItemHolder>(
        FavouriteBeverageDiffItemCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            : FavouriteBeverageItemHolder = FavouriteBeverageItemHolder.inflateFrom(parent)

    override fun onBindViewHolder(holder: FavouriteBeverageItemHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, clickListener)
    }

    class FavouriteBeverageItemHolder(val binding: FavouritebeverageItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun inflateFrom(parent: ViewGroup): FavouriteBeverageItemHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = FavouritebeverageItemBinding.inflate(layoutInflater, parent, false)
                return FavouriteBeverageItemHolder(binding)
            }
        }

        fun bind(item: FavouriteBeverage, clickListener: (favouriteBeverage: FavouriteBeverage) -> Unit) {
            binding.favouritebeverageName.text = item.beverageId.toString()
            binding.root.setOnClickListener {
                clickListener(item)
            }

        }
    }
}