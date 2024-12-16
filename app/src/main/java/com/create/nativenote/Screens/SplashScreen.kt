package com.create.nativenote.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.create.nativenote.Navigation.NotesNavigationItems
import com.create.nativenote.R
import com.create.nativenote.ui.theme.colorBlack
import kotlinx.coroutines.delay

@Composable

fun SplashScreen(navController: NavController, modifier: Modifier) {
    Scaffold { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(colorBlack)
        ) {
            Image(
                painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = "Logo",
                modifier = Modifier.align(Alignment.Center),
            )
        }
    }
    LaunchedEffect(Unit) {
        delay(2500)
        navController.navigate(
            route = NotesNavigationItems.HomeScreen.route,
//            "home" // Name of the destination
        ) {
            popUpTo(NotesNavigationItems.SplashScreen.route) {
                inclusive = true
            }

        }
    }
}