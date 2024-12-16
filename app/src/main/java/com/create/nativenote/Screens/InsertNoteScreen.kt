package com.create.nativenote.Screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.create.nativenote.Model.NotesModel
import com.create.nativenote.ui.theme.colorBlack
import com.create.nativenote.ui.theme.colorGray
import com.create.nativenote.ui.theme.colorLightGray
import com.create.nativenote.ui.theme.colorRed
import com.google.firebase.firestore.FirebaseFirestore

@Composable

fun InsertNoteScreen(navController: NavController, id: String?) {


    val context = LocalContext.current
    val firebaseDB = FirebaseFirestore.getInstance()
    val notesCollection = firebaseDB.collection("notes")

    /* State variables for title and description */
    val title = remember { mutableStateOf("") }
    val description = remember { mutableStateOf("") }



    LaunchedEffect(Unit) {

        if (id != "defaultId") {
            notesCollection.document(id.toString()).get().addOnSuccessListener {
                val singleData = it.toObject(NotesModel::class.java)
                title.value = singleData!!.title
                description.value = singleData.description
            }
        }
    }

    Scaffold(floatingActionButton = {
        FloatingActionButton(
            onClick = {
                if (title.value.isEmpty() && description.value.isEmpty()) {
                    Toast.makeText(context, "Enter Valid Data", Toast.LENGTH_SHORT).show()
                } else {
                    val myNoteId = if (id != "defaultId") {
                        id.toString()
                    } else {
                        notesCollection.document().id
                    }
                    val notes =
                        NotesModel(
                            id = myNoteId,
                            title = title.value,
                            description = description.value
                        )
                    notesCollection.document(myNoteId).set(notes).addOnCompleteListener {
                        if (it.isSuccessful) {
                            Toast.makeText(context, "Task Added", Toast.LENGTH_SHORT).show()
                            navController.popBackStack()
                        } else {
                            Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                }
            },
            containerColor = colorRed,
            contentColor = colorLightGray,
            shape = RoundedCornerShape(corner = CornerSize(50.dp)),
            content = {
                Icon(
                    imageVector = Icons.Default.Done,
                    contentDescription = "Done")}
        )

    }) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(color = colorBlack)

        ) {
            Column(modifier = Modifier.padding(15.dp)) {
                Text(
                    "Insert Note",
                    style = TextStyle(
                        fontSize = 32.sp,
                        color = colorLightGray,
                        fontWeight = FontWeight.Bold
                    )

                )
                Spacer(Modifier.height(10.dp))
                TextField(
                    textStyle = TextStyle(color = colorLightGray),
                    colors = TextFieldDefaults.colors(
//                        focusedTextColor = colorGray,
                        focusedContainerColor = colorGray,
                        unfocusedContainerColor = colorGray,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                    ),

                    shape = RoundedCornerShape(
                        topEnd = 18.dp,
                        bottomStart = 18.dp
                    ),
                    label = {
                        Text(
                            "Enter Your Title",
                            style = TextStyle(
                                fontSize = 18.sp,
                                color = colorLightGray
                            )
                        )
                    },
                    value = title.value,
                    onValueChange = { title.value = it },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(20.dp))
                TextField(
                    textStyle = TextStyle(color = colorLightGray),
                    colors = TextFieldDefaults.colors(
//                        focusedTextColor = colorGray,
                        focusedContainerColor = colorGray,
                        unfocusedContainerColor = colorGray,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                    ),

                    shape = RoundedCornerShape(
                        topEnd = 18.dp,
                        bottomStart = 18.dp
                    ),
                    label = {
                        Text(
                            "Enter Your Description",
                            style = TextStyle(
                                fontSize = 18.sp,
                                color = colorLightGray
                            )
                        )
                    },
                    value = description.value,
                    onValueChange = { description.value = it },
                    modifier = Modifier
                        .fillMaxHeight(0.6f)
                        .fillMaxWidth()
                )
            }
        }
    }
}