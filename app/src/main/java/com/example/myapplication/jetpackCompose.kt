package com.example.myapplication

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults


@Composable
fun TodoUi(modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color.Black, Color(0xFF0000FF))
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Card(
                modifier = Modifier
                    .padding(16.dp)
                    .height(100.dp)
                    .width(250.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFF001F3F)
                ),
                elevation = CardDefaults.cardElevation(8.dp)
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Welcome to Todo App",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                }
            }

            // 👇 Add input field below
            TodoUiWithEditDelete()
        }
    }
}

@Composable
fun TodoUiWithEditDelete() {
    var todos = remember { mutableStateListOf<String>() }
    var text by remember { mutableStateOf("") }
    var editIndex by remember { mutableStateOf<Int?>(null) } // track which todo is being edited

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(listOf(Color.Black, Color(0xFF0000FF)))
            )
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Welcome Card
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF001F3F)),
            elevation = CardDefaults.cardElevation(8.dp)
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Text(
                    "Welcome to Todo App",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Input + Add/Update Button
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF001F3F)),
            elevation = CardDefaults.cardElevation(8.dp)
        ) {
            Column(modifier = Modifier.padding(8.dp)) {
                TextField(
                    value = text,
                    onValueChange = { text = it },
                    label = { Text("Enter your task") },
                    placeholder = { Text("Type something...") },
                    singleLine = true,
                    colors = TextFieldDefaults.colors(
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        focusedContainerColor = Color(0xFF1A1A2E),
                        unfocusedContainerColor = Color(0xFF1A1A2E),
                        cursorColor = Color.Cyan,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedLabelColor = Color.Cyan,
                        unfocusedLabelColor = Color.LightGray,
                        focusedPlaceholderColor = Color.LightGray,
                        unfocusedPlaceholderColor = Color.DarkGray
                    ),
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                Button(
                    onClick = {
                        if (text.isNotBlank()) {
                            if (editIndex != null) {
                                todos[editIndex!!] = text.trim() // update task
                                editIndex = null
                            } else {
                                todos.add(text.trim()) // add new task
                            }
                            text = ""
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Blue,
                        contentColor = Color.White
                    ),
                    modifier = Modifier
                        .align(Alignment.End)
                        .width(120.dp)
                        .height(50.dp)
                ) {
                    Text(if (editIndex != null) "Update" else "Add")
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Todo List
        Column(modifier = Modifier.fillMaxWidth()) {
            todos.forEachIndexed { index, task ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFF1A1A2E)),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = task, color = Color.White)

                        Row {
                            // Edit Button
                            TextButton(onClick = {
                                text = task
                                editIndex = index
                            }) {
                                Text("Edit", color = Color.Cyan)
                            }

                            Spacer(modifier = Modifier.width(8.dp))

                            // Delete Button
                            TextButton(onClick = { todos.removeAt(index) }) {
                                Text("Delete", color = Color.Red)
                            }
                        }
                    }
                }
            }
        }
    }
}
