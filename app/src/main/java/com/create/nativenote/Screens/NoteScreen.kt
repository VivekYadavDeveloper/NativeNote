package com.create.nativenote.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.PopupProperties
import androidx.navigation.NavController
import com.create.nativenote.Model.NotesModel
import com.create.nativenote.Navigation.NotesNavigationItems
import com.create.nativenote.ui.theme.colorBlack
import com.create.nativenote.ui.theme.colorGray
import com.create.nativenote.ui.theme.colorLightGray
import com.create.nativenote.ui.theme.colorRed
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore


@Composable
//@Preview(showBackground = true, showSystemUi = true)
fun NoteScreen(navController: NavController) {

    val firebaseDB = FirebaseFirestore.getInstance()
    val notesCollection = firebaseDB.collection("notes")

    val notesList = remember { mutableStateListOf<NotesModel>() }

    val loading = remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        notesCollection.addSnapshotListener { value, error ->
            if (error == null) {
                val data = value?.toObjects(NotesModel::class.java)
                notesList.clear()
                notesList.addAll(data!!)
                loading.value = true
            } else {
                loading.value = false
            }
        }
    }

    /*
    val notesList = listOf(
    NotesModel(
    title = "Learn Jetpack Compose",
    description = "Learn How to build UI in Jetpack Compose"
    ),
    NotesModel(
    title = "Learn Firebase With Compose",
    description = "How to integrate Firebase in Compose"
    ),
    NotesModel(
    title = "Learn Supabase With Compose",
    description = "Implementing Supabase in Compose"
    ),
    NotesModel(
    title = "Learn FCM With Compose",
    description = "How To Use FCM In Compose"
    ),
    NotesModel(
    title = "Learn Authentication With Compose",
    description = "Google Authentication In Compose"
    ),
    NotesModel(
    title = "Learn Dependency Injection With Compose",
    description = "Lean How To Use Dependency Injection In Compose"
    ),


    )
    */

    Scaffold(floatingActionButton = {
        FloatingActionButton(
            onClick = { navController.navigate(route = NotesNavigationItems.InsertNoteScreen.route + "/defaultId") },
            containerColor = colorRed,
            contentColor = colorLightGray,
            shape = RoundedCornerShape(corner = CornerSize(50.dp)),
            content = {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add"
                )
            }
        )
    }) { innerPadding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(colorBlack)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(15.dp)
            ) {
                Text(
                    text = "Your Notes",
                    color = colorLightGray,
                    style = TextStyle(fontSize = 32.sp, fontWeight = FontWeight.Bold)
                )
                if (loading.value) {

                    LazyColumn {
                        items(notesList) { notes ->
                            ListItems(
                                notes = notes,
                                notesCollection = notesCollection,
                                navController = navController
                            )
                        }
                    }
                } else {
                    Box(modifier = Modifier.fillMaxSize()) {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .size(25.dp)
                                .align(alignment = Alignment.Center)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ListItems(
    notes: NotesModel,
    notesCollection: CollectionReference,
    navController: NavController,
) {

    var expanded by remember { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clip(shape = RoundedCornerShape(corner = CornerSize(10.dp)))
            .background(
                color = colorGray
            )

    ) {
        DropdownMenu(
            modifier = Modifier.background(color = Color.White),
            properties = PopupProperties(clippingEnabled = false),
            offset = DpOffset(x = -40.dp, y = 0.dp),
            expanded = expanded,
            onDismissRequest = { expanded = false },
        ) {
            DropdownMenuItem(
                text = {
                    Text(
                        text = "Update",
                        style = TextStyle(colorGray)
                    )
                },
                onClick = {
                    navController.navigate(NotesNavigationItems.InsertNoteScreen.route + "/${notes.id}")
                }
            )
            /*Delete Button*/
            DropdownMenuItem(
                text = {
                    Text(text = "Delete", style = TextStyle(colorGray))
                },
                onClick = {
                    showDialog = true
                }
            )
            if (showDialog) {
                AlertDialog(
                    onDismissRequest = { showDialog = false },
                    title = {
                        Text(text = "Delete Note")
                    },
                    text = {
                        Text(text = "Are you sure you want to delete this note?")
                    },
                    confirmButton = {
                        TextButton(
                            onClick = {
                                // Handle deletion here
                                notesCollection.document(notes.id).delete()
                                showDialog = false
                            }
                        ) {
                            Text("Yes")
                        }
                    },
                    dismissButton = {
                        TextButton(
                            onClick = {
                                showDialog = false
                            }
                        ) {
                            Text("No")
                        }
                    }
                )
            }

//            DropdownMenuItem(
//                text = {
//                    Text(
//                        text = "Delete",
//                        style = TextStyle(colorGray)
//                    )
//                },
//                onClick = {
//                    notesCollection.document(notes.id).delete()
//
//                }
//            )
        }

        Icon(
            imageVector = Icons.Default.MoreVert,
            contentDescription = "More",
            tint = colorLightGray,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(8.dp)
                .clickable {
                    expanded = true
                }
        )
        Column(modifier = Modifier.padding(8.dp)) {
            Text(
                notes.title,
                style = TextStyle(
                    color = colorLightGray,
                    fontSize = 25.sp, fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(5.dp)
            )

            Text(
                notes.description,
                style = TextStyle(
                    color = colorLightGray,
                    fontSize = 20.sp
                ),
                modifier = Modifier.padding(5.dp)
            )
        }
    }
}


