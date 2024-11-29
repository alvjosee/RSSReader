package com.example.rssreader.pages

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.rssreader.R

class FoodAdapter(private val foodList: List<FoodItem>) : RecyclerView.Adapter<FoodAdapter.FoodViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_food, parent, false)
        return FoodViewHolder(view)
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        val foodItem = foodList[position]
        holder.food.text = foodItem.food
        holder.caloricValue.text = foodItem.caloric_Value.toString()
        holder.protein.text = foodItem.protein.toString()
        holder.saturatedFats.text = foodItem.saturated_Fats.toString()
        holder.sugars.text = foodItem.sugars.toString()
        holder.fat.text = foodItem.fat.toString()
    }

    override fun getItemCount(): Int = foodList.size

    class FoodViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val food: TextView = itemView.findViewById(R.id.food)
        val caloricValue: TextView = itemView.findViewById(R.id.caloric_value)
        val protein: TextView = itemView.findViewById(R.id.protein)
        val saturatedFats: TextView = itemView.findViewById(R.id.saturated_fats)
        val sugars: TextView = itemView.findViewById(R.id.sugars)
        val fat: TextView = itemView.findViewById(R.id.fat)
    }
}
