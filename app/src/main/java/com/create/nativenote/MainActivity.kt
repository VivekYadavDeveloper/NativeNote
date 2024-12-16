package com.create.nativenote

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.create.nativenote.Navigation.NotesNavigation
import com.create.nativenote.Screens.SplashScreen
import com.create.nativenote.ui.theme.NativeNoteTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NativeNoteTheme {
                val navController = rememberNavController()
                NotesNavigation(navController)
//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    SplashScreen(
//                        modifier = Modifier.padding(innerPadding),
//                        navController = navController
//                    )
                }
            }
        }
    }



