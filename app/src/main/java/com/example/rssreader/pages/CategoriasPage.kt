package com.example.rssreader.pages
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import com.example.rssreader.pages.FoodService  // Cambia al paquete correcto

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material.Text
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.rssreader.AuthViewModel
import com.example.rssreader.BackButton
import com.example.rssreader.Centrado
import com.example.rssreader.R
import com.example.rssreader.SquareContainer
import com.example.rssreader.TitleText
import com.example.rssreader.BackButton
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.GET



// Modelo de datos
data class FoodItem(
    val food: String?,
    val caloric_Value: Int,
    val protein: Double,
    val saturated_Fats: Double,
    val sugars: Double,
    val fat: Double
)
// Interfaz Retrofit (asegúrate de que ya está configurada)
interface foodService {
    @GET("api/EZFit")  // Ajusta el endpoint según tu configuración
    fun getFoodData(): Call<List<FoodItem>>
}

// RetrofitClient (configurado previamente)
object RetrofitClients {
    private const val BASE_URL = "    https://c676-158-122-37-8.ngrok-free.app  "  // Cambia según tu configuración

    val foodService: FoodService by lazy {
        retrofit2.Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(retrofit2.converter.gson.GsonConverterFactory.create())
            .build()
            .create(FoodService::class.java)
    }
}

// ViewModel para manejar la lógica de negocio
class FoodViewModel : ViewModel() {
    var foodList by mutableStateOf<List<FoodItem>>(emptyList())
        private set

    var errorMessage by mutableStateOf("")
        private set

    fun fetchFoodData() {
        RetrofitClient.foodService.getFoodData().enqueue(object : Callback<List<FoodItem>> {
            override fun onResponse(call: Call<List<FoodItem>>, response: Response<List<FoodItem>>) {
                if (response.isSuccessful) {
                    foodList = response.body() ?: emptyList()
                } else {
                    errorMessage = "Error al cargar los datos: ${response.code()}"
                }
            }

            override fun onFailure(call: Call<List<FoodItem>>, t: Throwable) {
                errorMessage = "Error de red: ${t.message}"
            }
        })
    }
}

// Composable principal
@Composable
fun CategoriasPage(
    modifier: Modifier = Modifier,
    navController: NavController,
    authViewModel: AuthViewModel
) {
    val Wine = colorResource(id = R.color.Wine) // Define el color que quieres usar
    val White = colorResource(id = R.color.white) // Define el color que quieres usar
    BackButton({ navController.navigate("home") } ,modifier = Modifier)
    Column() {
        SquareContainer(
            backgroundColor = Wine,  // Color de fondo
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.2f)
        ) {
            Centrado() {
                TitleText(
                    text = "Categorías",  // El texto que se mostrará en el título
                    color = White,
                    textAlign = TextAlign.Center
                )
            }
        }

        Spacer(modifier = Modifier.height(40.dp))
        // Instancia del ViewModel
        val foodViewModel = remember { FoodViewModel() }

        // Llama a la API al cargar el Composable
        LaunchedEffect(Unit) {
            foodViewModel.fetchFoodData()
        }

        // UI
        Column(modifier = modifier.fillMaxSize().padding(16.dp)) {
            if (foodViewModel.errorMessage.isNotEmpty()) {
                // Muestra un mensaje de error si lo hay
                Text(text = foodViewModel.errorMessage, modifier = Modifier.padding(16.dp))
            } else {
                // Muestra los datos en una tabla (LazyColumn)
                LazyColumn {
                    items(foodViewModel.foodList) { foodItem ->
                        FoodRow(foodItem)
                    }
                }
            }
        }
    }
}

// Composable para cada fila de la tabla
@Composable
fun FoodRow(foodItem: FoodItem) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = foodItem.food ?: "Desconocido", modifier = Modifier.weight(1f))
        Text(text = foodItem.caloric_Value.toString(), modifier = Modifier.weight(1f))
        Text(text = foodItem.protein.toString(), modifier = Modifier.weight(1f))
        Text(text = foodItem.saturated_Fats.toString(), modifier = Modifier.weight(1f))
        Text(text = foodItem.sugars.toString(), modifier = Modifier.weight(1f))
        Text(text = foodItem.fat.toString(), modifier = Modifier.weight(1f))
    }

}
