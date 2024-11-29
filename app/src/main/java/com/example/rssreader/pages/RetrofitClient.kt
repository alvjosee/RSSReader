package com.example.rssreader.pages
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.rssreader.pages.FoodService


object RetrofitClient {
    private const val BASE_URL = "   https://c676-158-122-37-8.ngrok-free.app   "  // Ajusta la URL base según tu configuración

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())  // Convierte el JSON a objetos Kotlin usando Gson
        .build()

    val foodService: FoodService = retrofit.create(FoodService::class.java)
}
