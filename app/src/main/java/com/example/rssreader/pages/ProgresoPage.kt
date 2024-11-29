package com.example.rssreader.pages
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.rssreader.AuthViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun ProgresoPage(
    modifier: Modifier = Modifier,
    navController: NavController,
    authViewModel: AuthViewModel
) {
    var weight by remember { mutableStateOf("") }
    var height by remember { mutableStateOf("") }
    var imc by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(true) }

    val auth = FirebaseAuth.getInstance()
    val currentUser = auth.currentUser

    if (currentUser != null) {
        val db = FirebaseFirestore.getInstance()
        val uid = currentUser.uid

        // Cargar datos desde Firestore
        LaunchedEffect(Unit) {
            db.collection("info")
                .document(uid)
                .get()
                .addOnSuccessListener { document ->
                    if (document.exists()) {
                        name = document.getString("name") ?: ""
                        lastName = document.getString("lastName") ?: ""
                        weight = document.getString("weight") ?: ""
                        height = document.getString("height") ?: ""
                        imc = document.getString("IMC") ?: ""
                    }
                    isLoading = false
                }
                .addOnFailureListener { e ->
                    println("Error al cargar datos: ${e.message}")
                    isLoading = false
                }
        }
    }

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (isLoading) {
            Text(text = "Cargando datos...", fontSize = 24.sp)
        } else {
            Text(text = "Tus datos", fontSize = 32.sp)
            Spacer(modifier = Modifier.height(16.dp))


            Text(text = "Nombre: $name", fontSize = 18.sp)
            Text(text = "Apellido: $lastName", fontSize = 18.sp)
            Text(text = "Peso: $weight kg", fontSize = 18.sp)
            Text(text = "Estatura: $height m", fontSize = 18.sp)
            Text(text = "IMC: $imc", fontSize = 18.sp)

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text(text = "Nombre") }
            )
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = lastName,
                onValueChange = { lastName = it },
                label = { Text(text = "Apellido") }
            )
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = weight,
                onValueChange = { weight = it },
                label = { Text(text = "Peso (kg)") }
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = height,
                onValueChange = { height = it },
                label = { Text(text = "Estatura (cm)") }
            )

            Spacer(modifier = Modifier.height(8.dp))

            Button(onClick = {
                if (currentUser != null) {
                    val db = FirebaseFirestore.getInstance()
                    val nombre = name
                    val apellido = lastName

                    val pesoDouble = weight.toDoubleOrNull()
                    val alturaDouble = height.toDoubleOrNull()?.div(100)

                    if (pesoDouble != null && alturaDouble != null && alturaDouble > 0) {
                        val imcValue = (pesoDouble / (alturaDouble * alturaDouble)).let {
                            String.format("%.2f", it).toDouble()
                        }

                        val userData = hashMapOf(
                            "IMC" to imcValue.toString(),
                            "height" to (alturaDouble).toString(),
                            "weight" to pesoDouble.toString(),
                            "name" to nombre,
                            "lastName" to apellido

                        )

                        val uid = currentUser.uid
                        db.collection("info")
                            .document(uid)
                            .set(userData)
                            .addOnSuccessListener {
                                println("Datos guardados correctamente en Firestore")
                                imc = imcValue.toString() // Actualizar el valor de IMC en la UI
                            }
                            .addOnFailureListener { e ->
                                println("Error al guardar los datos: ${e.message}")
                            }
                    } else {
                        println("Por favor, ingresa valores válidos para peso y altura.")
                    }
                }
            }) {
                Text(text = "Guardar")
            }
            Button(onClick = {
                if (currentUser != null) {
                    val db = FirebaseFirestore.getInstance()
                    val uid = currentUser.uid

                    db.collection("info")
                        .document(uid)
                        .delete()
                        .addOnSuccessListener {
                            println("Documento eliminado correctamente.")
                            weight = ""
                            height = ""
                            imc = ""
                            name = ""
                            lastName = ""
                        }
                        .addOnFailureListener { e ->
                            println("Error al eliminar el documento: ${e.message}")
                        }
                }
            }) {
                Text(text = "Borrar Progreso")
            }
            Spacer(modifier = Modifier.height(8.dp))

            TextButton(onClick = {
                navController.navigate("home")
            }) {
                Text(text = "Home Page")
            }
        }
    }
}
