package com.create.nativenote.Navigation

sealed class NotesNavigationItems(val route: String) {

    object SplashScreen : NotesNavigationItems("splash")
    object HomeScreen : NotesNavigationItems("home")
    object InsertNoteScreen : NotesNavigationItems("insertNoteScreen")
}