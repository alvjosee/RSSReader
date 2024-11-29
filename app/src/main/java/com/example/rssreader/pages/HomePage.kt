package com.example.rssreader.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.rssreader.AuthState
import com.example.rssreader.AuthViewModel
import com.example.rssreader.R

@Composable
fun HomePage(
    modifier: Modifier = Modifier,
    navController: NavController,
    authViewModel: AuthViewModel
) {
    val authState = authViewModel.authState.observeAsState()
// En Home, extrae los parámetros de la URL
    val navBackStackEntry = navController.currentBackStackEntryAsState()
    val name = navBackStackEntry.value?.arguments?.getString("name") ?: "Nombre no disponible"
    val lastName = navBackStackEntry.value?.arguments?.getString("lastName") ?: "Apellido no disponible"

// Usa esos valores en la UI
    Text(
        text = "Alvaro Trujillo", // Muestra el nombre completo
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        color = Color.Black
    )

    LaunchedEffect(authState.value) {
        when (authState.value) {
            is AuthState.Unauthenticated -> navController.navigate("login")
            else -> Unit
        }
    }

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Cuadro superior
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.2f) // Aproximadamente 20% de la altura típica

                .background(Color(0xFFADD8E6)), // Azul clarito
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                // Imagen
                Image(
                    painter = painterResource(id = R.drawable.ic_sample_image), // Reemplaza con tu recurso
                    contentDescription = "Imagen de perfil",
                    modifier = Modifier
                        .size(64.dp)
                        .clip(RoundedCornerShape(8.dp))
                )

                Spacer(modifier = Modifier.width(16.dp))
                Column(
                    verticalArrangement =Arrangement.SpaceEvenly
                )
                {
                    Text(
                        text = "Bienvenido,", // Personaliza con datos dinámicos si es necesario
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Light,
                        color = Color.Gray
                    )
                    // Nombre
                    Text(
                        text = "Alvaro Trujillo", // Personaliza con datos dinámicos si es necesario
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Resto del contenido

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Inicio", fontSize = 32.sp)
            Spacer(modifier = Modifier.height(10.dp))

            ButtonContainer(
                backgroundColor = Color(0xFFD5D5D5),
                cornerRadius = 16.dp, // Esquinas redondeadas
                padding = 20.dp, // Espaciado alrededor
            ){
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                )
                {
                    Spacer(modifier = Modifier.height(35.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {


                        Boton(

                            text = "Categorías",
                            color = Color(0xFFFF7043),
                            onClick = { navController.navigate("categorias") },
                            imgId = R.drawable.categorias
                        )






                        Boton(
                            text = "Rutinas",
                            color = Color(0xFF9575CD),
                            onClick = { navController.navigate("rutinas") },
                            imgId = R.drawable.rutinas
                        )

                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {



                        Boton(
                            text = "Dietas",
                            color = Color(0xFF64B5F6),
                            onClick = { navController.navigate("dietas") },
                            imgId = R.drawable.dietas
                        )



                        Boton(
                            text = "Progreso",
                            color = Color(0xFFE0E0E0),
                            onClick = { navController.navigate("progreso") },
                            imgId = R.drawable.progreso
                        )

                    }
                    Spacer(modifier = Modifier.height(35.dp))
                }
            }
            TextButton(

                onClick = {
                    authViewModel.signout() }
            ) {
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = "Salir", textAlign = TextAlign.End,fontSize = 25.sp)
                Spacer(modifier = Modifier.height(10.dp))
            }
        }
    }
}


@Composable
fun Boton(
    text: String,
    color: Color,
    onClick: () -> Unit,
    imgId: Int
) {
    ElevatedButton(
        shape = RoundedCornerShape(14.dp),

        colors = ButtonDefaults.elevatedButtonColors(containerColor = color),
        onClick = onClick, // Esquinas rectas
        modifier = Modifier.size(120.dp)
    ) {
        Text(
            text = text,
            color = Color.White,
            fontSize = 0.sp,
            fontWeight = FontWeight.Bold
        )
        Image(
            painter = painterResource(id = imgId),
            contentDescription = "",
            modifier = Modifier
                .size(100.dp)
        )
    }
}


@Composable
fun ButtonContainer(
    modifier: Modifier = Modifier,
    backgroundColor: Color, // Color de fondo
    cornerRadius: Dp, // Radio de esquinas
    padding: Dp, // Padding alrededor del contenedor
    content: @Composable () -> Unit // Contenido dentro del contenedor (los botones)
) {
    Box(
        modifier = modifier
            .fillMaxWidth() // Ocupa todo el ancho disponible
            .padding(padding) // Agrega padding alrededor del contenedor
            .clip(RoundedCornerShape(cornerRadius)) // Esquinas redondeadas
            .background(backgroundColor) // Color de fondo
    ) {
        content() // Coloca el contenido dentro del contenedor
    }
}



