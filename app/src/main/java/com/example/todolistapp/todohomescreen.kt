package com.example.todolistapp

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TodoHomeScreen() {
    var task by remember { mutableStateOf("") }
    var tasks = remember { mutableStateListOf<String>() }

    Column(modifier = Modifier.padding(16.dp)) {
        OutlinedTextField(
            value = task,
            onValueChange = { task = it },
            label = { Text("Enter a Task") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(8.dp))
        Button(
            onClick = {
                if (task.isNotBlank()) {
                    tasks.add(task)
                    task = ""
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Add Task")
        }
        Spacer(Modifier.height(16.dp))
        LazyColumn {
            items(tasks) { item ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Text(
                        text = item,
                        modifier = Modifier.padding(12.dp),
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }
    }
}
