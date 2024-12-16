package com.create.nativenote.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.create.nativenote.Model.NotesModel
import com.create.nativenote.ui.theme.colorBlack
import com.create.nativenote.ui.theme.colorGray
import com.create.nativenote.ui.theme.colorLightGray
import com.create.nativenote.ui.theme.colorRed


@Composable
@Preview(showBackground = true, showSystemUi = true)
fun NoteScreen() {


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
    Scaffold(floatingActionButton = {
        FloatingActionButton(
            onClick = { /*TODO*/ },
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
                LazyColumn {
                    items(notesList) { notes ->
                        ListItems(notes)

                    }
                }
            }
        }
    }


}

@Composable
fun ListItems(notes: NotesModel) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clip(shape = RoundedCornerShape(corner = CornerSize(10.dp)))
            .background(
                color = colorGray
            )

    ) {
        Icon(
            imageVector = Icons.Default.MoreVert,
            contentDescription = "More",
            tint = colorLightGray,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(8.dp)
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


