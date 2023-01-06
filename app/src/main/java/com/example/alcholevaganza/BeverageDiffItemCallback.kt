package com.example.alcholevaganza

import androidx.recyclerview.widget.DiffUtil
import com.example.alcholevaganza.database.Beverage

class BeverageDiffItemCallback : DiffUtil.ItemCallback<Beverage>() {
    override fun areItemsTheSame(oldItem: Beverage, newItem: Beverage)
     = (oldItem.Id == newItem.Id)

    override fun areContentsTheSame(oldItem: Beverage, newItem: Beverage)
    =(oldItem == newItem)


}