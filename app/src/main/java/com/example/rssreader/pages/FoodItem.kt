package com.example.rssreader.pages

data class FoodItems(
    val food: String?,
    val caloric_Value: Int,
    val protein: Double,
    val saturated_Fats: Double,
    val sugars: Double,
    val fat: Double
)