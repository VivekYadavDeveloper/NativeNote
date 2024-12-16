package com.create.nativenote.Navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.create.nativenote.Screens.InsertNoteScreen
import com.create.nativenote.Screens.NoteScreen
import com.create.nativenote.Screens.SplashScreen


@Composable
fun NotesNavigation(navController: NavHostController, modifier: Modifier = Modifier) {


    NavHost(navController = navController, startDestination = "splash") {
        composable(route = "splash") {
            SplashScreen(navController, modifier = Modifier)
        }

        composable(route = "home") {
            NoteScreen(navController)
        }
        composable(route = "insertNoteScreen" + "/{id}") {
            val id = it.arguments?.getString("id")
            InsertNoteScreen(navController ,id)

        }
    }
}