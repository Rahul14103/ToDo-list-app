package com.example.todolistapp

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun TimeManagementScreen() {
    var timeLeft by remember { mutableStateOf(25 * 60) }
    var running by remember { mutableStateOf(false) }

    LaunchedEffect(running) {
        while (running && timeLeft > 0) {
            delay(1000)
            timeLeft--
        }
    }

    val minutes = timeLeft / 60
    val seconds = timeLeft % 60

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text("Focus Timer", style = MaterialTheme.typography.headlineSmall)
        Spacer(Modifier.height(20.dp))
        Text(String.format("%02d:%02d", minutes, seconds), style = MaterialTheme.typography.displayMedium)
        Spacer(Modifier.height(20.dp))
        Button(onClick = { running = !running }) {
            Text(if (running) "Pause" else "Start")
        }
    }
}
