package com.example.ms.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ms.R
import com.example.ms.data.DishItem

class DishAdapter(private val context:Context, private val dishItem: MutableList<DishItem>):RecyclerView.Adapter<DishAdapter.DishViewHolder>(){

//   private val dishes : ArrayList<DishItem> = ArrayList()

    class DishViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val dishName: TextView = itemView.findViewById<TextView>(R.id.tvDishName)
        val dishPrice: TextView = itemView.findViewById<TextView>(R.id.tvPriceInt)
        val dishWeight: TextView = itemView.findViewById<TextView>(R.id.tvQuantityInt)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DishViewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.item_dish,parent,false)
        return DishViewHolder(view)
    }

    override fun onBindViewHolder(holder: DishViewHolder, position: Int) {
        val dish: DishItem = dishItem[position]
        holder.dishName.text  = dish.name
        holder.dishPrice.text = dish.price.toString()
        holder.dishWeight.text = dish.score.toString()
    }

    fun updateDishes(updatedDishes : ArrayList<DishItem>){
        dishItem.clear()
        dishItem.addAll(updatedDishes)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return dishItem.size
    }
}