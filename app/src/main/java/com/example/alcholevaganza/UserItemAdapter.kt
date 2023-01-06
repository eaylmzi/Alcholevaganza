package com.example.alcholevaganza

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.alcholevaganza.database.User

class UserItemAdapter : RecyclerView.Adapter<UserItemAdapter.UserItemViewHolder>() {
    var data = listOf<User>()

        set(value) {
            field = value
            notifyDataSetChanged()
        }


    override fun getItemCount() = data.size
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            : UserItemViewHolder = UserItemViewHolder.inflateFrom(parent)

    override fun onBindViewHolder(holder: UserItemViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
    }

    class UserItemViewHolder(val rootView: TextView) : RecyclerView.ViewHolder(rootView) {
        companion object {
            fun inflateFrom(parent: ViewGroup): UserItemViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater
                    .inflate(R.layout.user_item, parent, false) as TextView
                return UserItemViewHolder(view)
            }
        }

        fun bind(item: User) {
            rootView.text = item.userEmail
        }
    }
}