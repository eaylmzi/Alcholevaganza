package com.example.alcholevaganza

import com.example.alcholevaganza.database.Beverage


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.alcholevaganza.databinding.BeverageItemBinding


class BeverageItemAdapter(val clickListener :(beverage : Beverage) ->Unit)
    : ListAdapter<Beverage, BeverageItemAdapter.BeverageItemHolder>(BeverageDiffItemCallback()){




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            : BeverageItemHolder = BeverageItemHolder.inflateFrom(parent)

    override fun onBindViewHolder(holder: BeverageItemHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item,clickListener)
    }

    class BeverageItemHolder(val binding: BeverageItemBinding)
        : RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun inflateFrom(parent: ViewGroup): BeverageItemHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = BeverageItemBinding.inflate(layoutInflater,parent,false)
                return BeverageItemHolder(binding)
            }
        }

        fun bind(item: Beverage,clickListener: (beverage: Beverage) -> Unit) {
            binding.beverageName.text = item.beverageName

       //    val imageString =item.beverageName+item.Id.toString()
        //  val resourceId =resources.getIdentifier(imageString, "drawable", context.packageName)
         // binding.beverageImage.setImageResource(resourceId)

            binding.root.setOnClickListener{
                clickListener(item)
            }

        }

    }
}