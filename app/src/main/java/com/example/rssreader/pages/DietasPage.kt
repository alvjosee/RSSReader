package com.example.rssreader.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.rssreader.AuthViewModel
import com.example.rssreader.BackButton
import com.example.rssreader.BodyText
import com.example.rssreader.Centrado
import com.example.rssreader.CustomButton
import com.example.rssreader.R
import com.example.rssreader.ScrollableRowWithButtons
import com.example.rssreader.SquareContainer
import com.example.rssreader.SubtitleText
import com.example.rssreader.TitleText

@Composable
fun DietasPage(modifier: Modifier = Modifier, navController: NavController, authViewModel: AuthViewModel)  {
    val Brown = colorResource(id = R.color.Brown) // Define el color que quieres usar
    val White = colorResource(id = R.color.white) // Define el color que quieres usar
    val Orange = colorResource(id = R.color.Orange) // Define el color que quieres usar
    val Gray = colorResource(id = R.color.Gray) // Define el color que quieres usar

    BackButton({ navController.navigate("home") }, modifier = Modifier)
    Column() {
        SquareContainer(
            backgroundColor = Orange,  // Color de fondo
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.2f)

        ) {
            Centrado() {
                TitleText(
                    text = "Dietas",  // El texto que se mostrará en el título
                    color = White,
                    textAlign = TextAlign.Center
                )
            }
        }
        Spacer(modifier = Modifier.height(20.dp))


        Column(
            verticalArrangement = Arrangement.Center, // Espaciado entre los botones
            horizontalAlignment = Alignment.CenterHorizontally, // Centra verticalmente el contenido
            modifier = Modifier
                // Hace que la Row sea desplazable horizontalmente
                .padding(all=20.dp)  // Espaciado interno// Agrega un fondo gris
                .fillMaxWidth()
        ) {
            ScrollableRowWithButtons()
        }










    }}