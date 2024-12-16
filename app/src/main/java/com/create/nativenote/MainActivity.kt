package com.create.nativenote

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.create.nativenote.Navigation.NotesNavigation
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




