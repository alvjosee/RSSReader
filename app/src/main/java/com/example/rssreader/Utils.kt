package com.example.rssreader

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextAlign.Companion.Center
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.res.painterResource


@Composable
fun CustomButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    color: Color = Color.Black, // Color de fondo del botón
    textColor: Color = Color.White, // Color del texto
    fontSize: Float = 16f, // Tamaño del texto en sp
    fontWeight: FontWeight = FontWeight.Normal, // Peso del texto (Bold, Medium, Light, etc.)
    shape: Shape = RoundedCornerShape(8.dp), // Forma del botón (esquinas redondeadas o cuadradas)
    elevation: ButtonElevation? = ButtonDefaults.buttonElevation(), // Elevación del botón
    padding: Dp = 8.dp // Espaciado alrededor del botón
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = color),
        modifier = modifier.padding(padding), // Padding externo
        shape = shape, // Forma del botón
        elevation = elevation // Controla la sombra o elevación
    ) {
        Text(
            text = text,
            fontSize = fontSize.sp,
            color = textColor,
            fontWeight = fontWeight // Estilo del texto
        )
    }
}

@Composable
fun SquareContainer(
    backgroundColor: Color,
    modifier: Modifier = Modifier ,
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .background(backgroundColor)  // Aplica el color de fondo
            .clip(RoundedCornerShape(8.dp))  // Aplica las esquinas redondeadas
            .padding(16.dp)  // Añade padding dentro del contenedor
            .zIndex(2f)
    ) {
        content()  // Muestra el contenido dentro del Box

    }
}


@Composable
fun TitleText(
    text: String,
    modifier: Modifier = Modifier,
    fontSize: Float = 24f, // Tamaño del texto (predeterminado: 24sp)
    fontWeight: FontWeight = FontWeight.Bold, // Peso de la fuente
    color: Color,// = Color.Black, // Color del texto
    textAlign: TextAlign = TextAlign.Center, // Alineación del texto
    padding: Dp = 16.dp // Padding externo
) {

    Text(
        text = text,
        modifier = modifier
            .padding(padding),
        fontSize = fontSize.sp,
        fontWeight = fontWeight,
        color = color,
        textAlign = textAlign
    )
}

// Función para Subtítulos
@Composable
fun SubtitleText(
    text: String,
    modifier: Modifier = Modifier,
    fontSize: Float = 18f, // Tamaño del texto (predeterminado: 18sp)
    fontWeight: FontWeight = FontWeight.Medium, // Peso de la fuente
    color: Color = Color.White, // Color del texto
    textAlign: TextAlign = TextAlign.Start, // Alineación del texto
    padding: Dp = 16.dp // Padding externo
) {
    Text(
        text = text,
        modifier = modifier.padding(padding),
        fontSize = fontSize.sp,
        fontWeight = fontWeight,
        color = color,
        textAlign = textAlign
    )
}

// Función para Texto Normal
@Composable
fun BodyText(
    text: String,
    modifier: Modifier = Modifier,
    fontSize: Float = 14f, // Tamaño del texto (predeterminado: 14sp)
    color: Color = Color.DarkGray, // Color del texto
    lineHeight: Float = 20f, // Altura de línea
    textAlign: TextAlign = TextAlign.Justify, // Alineación del texto
    padding: Dp = 4.dp // Padding externo
) {
    Text(
        text = text,
        modifier = modifier.padding(padding),
        fontSize = fontSize.sp,
        color = color,
        lineHeight = lineHeight.sp,
        textAlign = textAlign
    )
}
@Composable
fun BackButton(

    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val morado = colorResource(id = R.color.white)
    androidx.compose.foundation.layout.Box(
        modifier = modifier
            .padding(16.dp) // Padding externo para separar del borde
            .size(40.dp) // Tamaño del botón (cuadrado pequeño)
            .clip(RoundedCornerShape(12.dp)) // Esquinas semi-redondeadas
            .background(morado) // Color morado
            .clickable(onClick = onBackClick) // Acción al hacer clic
            .zIndex(1f),
        contentAlignment = Alignment.Center, // Centra el contenido dentro del botón

    ) {
        Text(
            text = "←", // Flecha para indicar "regresar"
            color = Color.Black, // Color del texto
            fontSize = 30.sp, // Tamaño de la flecha
            fontWeight = FontWeight.Bold, // Grosor de la flech
            fontFamily = FontFamily.Monospace



        )
    }
}

//Columnas y Rows para orden de elementos
@Composable
fun Centrado(content: @Composable () -> Unit) {
    Column(

        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        content() // Muestra el texto centrado
    }
}

@Composable
fun CentradoVertical(content: @Composable () -> Unit) {
    val scrollState = rememberScrollState()
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxHeight()
            .verticalScroll(scrollState)
    ) {
        content() // Muestra el texto centrado
    }
}
@Composable
fun ScrollableRowWithButtons() {
    // Estado para almacenar cuál imagen mostrar
    var selectedImage by remember { mutableStateOf<Int?>(null) } // null indica que no hay imagen seleccionada

    //Crea una fila desplazable con botones
    Row(
        modifier = Modifier.horizontalScroll(rememberScrollState())
    ) {
    //Botones que actualizarán el estado cuando se hace clic
        CustomButton(text = "Lunes", onClick = { selectedImage = R.drawable.lunes})
        CustomButton(text = "Martes", onClick = { selectedImage = R.drawable.martes })
        CustomButton(text = "Miércoles", onClick = { selectedImage = R.drawable.miercoles})
        CustomButton(text = "Jueves", onClick = { selectedImage = R.drawable.jueves })
        CustomButton(text = "Viernes", onClick = { selectedImage = R.drawable.viernes})
    }

    // Mostrar la imagen correspondiente según el estado seleccionado
    if (selectedImage != null) {
        Image(
            painter = painterResource(id = selectedImage!!),
            contentDescription = "Imagen seleccionada",
            modifier = Modifier.fillMaxWidth().padding(top = 16.dp)
        )
    }
}




