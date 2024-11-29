package com.example.rssreader

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.rssreader.pages.CategoriasPage
import com.example.rssreader.pages.DietasPage
import com.example.rssreader.pages.HomePage
import com.example.rssreader.pages.LoginPage
import com.example.rssreader.pages.ProgresoPage
import com.example.rssreader.pages.RutinasPage
import com.example.rssreader.pages.SignUpPage

@Composable
fun MyAppNavigation(modifier: Modifier = Modifier,authViewModel: AuthViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "login", builder= {
        composable("login"){
            LoginPage(modifier,navController,authViewModel)
        }
        composable("signup"){
            SignUpPage(modifier,navController,authViewModel)
        }
        composable("home") {
            HomePage(modifier, navController, authViewModel)
        }
        composable("dietas"){
            DietasPage(modifier,navController,authViewModel)
        }
        composable("rutinas"){
            RutinasPage(modifier,navController,authViewModel)
        }
        composable("categorias"){
            CategoriasPage(modifier,navController,authViewModel)
        }
        composable("progreso"){
            ProgresoPage(modifier,navController,authViewModel)
        }
    } )
}





