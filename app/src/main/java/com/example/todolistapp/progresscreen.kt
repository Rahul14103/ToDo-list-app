package com.example.todolistapp

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ProgressScreen() {
    var progress by remember { mutableStateOf(0.65f) }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Task Progress", style = MaterialTheme.typography.headlineSmall)
        Spacer(Modifier.height(20.dp))
        LinearProgressIndicator(progress = progress, modifier = Modifier.fillMaxWidth(0.8f))
        Spacer(Modifier.height(20.dp))
        Text("${(progress * 100).toInt()}% completed")
    }
}
