package com.example.rssreader.pages

import android.text.style.BackgroundColorSpan
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.rssreader.AuthViewModel
import com.example.rssreader.BackButton
import com.example.rssreader.Centrado
import com.example.rssreader.CentradoVertical
import com.example.rssreader.R
import com.example.rssreader.SquareContainer
import com.example.rssreader.SubtitleText
import com.example.rssreader.TitleText

@Composable
fun RutinasPage(modifier: Modifier = Modifier, navController: NavController, authViewModel: AuthViewModel) {
    val Brown = colorResource(id = R.color.Brown) // Define el color que quieres usar
    val White = colorResource(id = R.color.white) // Define el color que quieres usar
    val Lblue = colorResource(id = R.color.Lblue) // Define el color que quieres usar
    val imgId = R.drawable.principiante
    val imgId2 = R.drawable.intermedio
    val imgId3 = R.drawable.avanzado
    BackButton({ navController.navigate("home") }, modifier = Modifier)
    CentradoVertical() {
        SquareContainer(
            backgroundColor = Brown,  // Color de fondo
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.3f)
        ) {
            Centrado() {
                TitleText(
                    text = "Rutinas",  // El texto que se mostrará en el título
                    color = White,
                    textAlign = TextAlign.Center
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {


                ButtonContainer(
                    backgroundColor = Lblue,
                    cornerRadius = 16.dp, // Esquinas redondeadas
                    padding = 20.dp, // Espaciado alrededor
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .fillMaxWidth())
                    {
                        SubtitleText(
                            text = "Principiante",
                            textAlign = TextAlign.Center
                        )
                    Image(
                        painter = painterResource(id = imgId),
                        contentDescription = "",
                        alignment = Alignment.Center,
                        modifier = Modifier
                            .size(200.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            ButtonContainer(
                backgroundColor = Lblue,
                cornerRadius = 16.dp, // Esquinas redondeadas
                padding = 20.dp, // Espaciado alrededor
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth())
                {
                    SubtitleText(text = "Intermedio",
                        textAlign = TextAlign.Center)
                    Image(
                        painter = painterResource(id = imgId2),
                        contentDescription = "",
                        alignment = Alignment.Center,
                        modifier = Modifier
                            .size(200.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            ButtonContainer(
                backgroundColor = Lblue,
                cornerRadius = 16.dp, // Esquinas redondeadas
                padding = 20.dp, // Espaciado alrededor
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth())
                {
                    SubtitleText(
                        text = "Avanzado",
                        textAlign = TextAlign.Center
                    )
                    Image(
                        painter = painterResource(id = imgId3),
                        contentDescription = "",
                        alignment = Alignment.Center,
                        modifier = Modifier
                            .size(200.dp)
                    )
                }
            }



        }
    }
}