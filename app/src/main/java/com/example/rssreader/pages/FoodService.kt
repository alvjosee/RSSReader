package com.example.rssreader.pages

import retrofit2.Call
import retrofit2.http.GET

interface FoodService {
    @GET("api/EZFit")
    fun getFoodData(): Call<List<FoodItem>>  // Esta llamada obtiene una lista de FoodItem
}
