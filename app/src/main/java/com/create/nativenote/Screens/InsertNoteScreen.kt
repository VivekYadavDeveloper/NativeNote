package com.create.nativenote.Screens

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.create.nativenote.ui.theme.colorBlack
import com.create.nativenote.ui.theme.colorGray
import com.create.nativenote.ui.theme.colorLightGray
import com.create.nativenote.ui.theme.colorRed

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun InsertNoteScreen(modifier: Modifier? = null) {
    Scaffold(floatingActionButton = {
        FloatingActionButton(
            onClick = {
                /*TODO : Insert Note*/
            },
            containerColor = colorRed,
            contentColor = colorLightGray,
            shape = RoundedCornerShape(corner = CornerSize(50.dp)),
            content = {
                Icon(
                    imageVector = Icons.Default.Done,
                    contentDescription = "Done"
                )
            }
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
                    value = "",
                    onValueChange = { /*TODO : Title*/ },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(20.dp))
                TextField(
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
                    value = "",
                    onValueChange = { /*TODO : Description*/ },
                    modifier = Modifier
                        .fillMaxHeight(0.6f)
                        .fillMaxWidth()
                )
            }
        }
    }
}