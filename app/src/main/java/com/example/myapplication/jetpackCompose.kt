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
            InputWithTodo()
        }
    }
}

@Composable
fun InputWithTodo() {
    var text by remember { mutableStateOf("") }
    val todoList = remember { mutableStateListOf<String>() }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        // Input + Button card
        Card(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .height(150.dp), // taller to fit input + button
            colors = CardDefaults.cardColors(containerColor = Color(0xFF001F3F)),
            elevation = CardDefaults.cardElevation(8.dp)
        ) {
            Column(modifier = Modifier.padding(8.dp)) {
                TextField(
                    value = text,
                    onValueChange = { text = it },
                    label = { Text("Enter your task here properly") },
                    placeholder = { Text("Enter something...") },
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
                            todoList.add(text.trim())
                            text = "" // clear input after adding
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Blue,
                        contentColor = Color.White
                    ),
                    modifier = Modifier
                        .align(Alignment.End)
                        .width(100.dp)
                        .height(50.dp)
                ) {
                    Text("Add")
                }
            }
        }

        // Display the list of todos
        Column(modifier = Modifier.fillMaxWidth()) {
            todoList.forEach { task ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 4.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFF1A1A2E)),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Text(
                        text = task,
                        color = Color.White,
                        modifier = Modifier.padding(12.dp)
                    )
                }
            }
        }
    }
}
