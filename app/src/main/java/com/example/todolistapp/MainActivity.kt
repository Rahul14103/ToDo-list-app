package com.example.todolistapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.todolistapp.ui.theme.TodoListAppTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.foundation.layout.padding


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TodoListAppTheme {
                AppNavigation()
            }
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    var loggedInRole by remember { mutableStateOf<String?>(null) }

    if (loggedInRole == null) {
        LoginScreen(onLogin = { role ->
            loggedInRole = role
        })
    } else {
        MainAppScaffold(navController, loggedInRole!!)
    }
}

@Composable
fun MainAppScaffold(navController: NavHostController, role: String) {
    var selectedTab by remember { mutableStateOf("home") }

    Scaffold(
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = selectedTab == "home",
                    onClick = { selectedTab = "home"; navController.navigate("home") },
                    label = { Text("Home") },
                    icon = { Icon(Icons.Default.Home, null) }
                )
                NavigationBarItem(
                    selected = selectedTab == "progress",
                    onClick = { selectedTab = "progress"; navController.navigate("progress") },
                    label = { Text("Progress") },
                    icon = { Icon(Icons.Default.BarChart, null) }
                )
                NavigationBarItem(
                    selected = selectedTab == "time",
                    onClick = { selectedTab = "time"; navController.navigate("time") },
                    label = { Text("Time") },
                    icon = { Icon(Icons.Default.Timer, null) }
                )
                NavigationBarItem(
                    selected = selectedTab == "profile",
                    onClick = { selectedTab = "profile"; navController.navigate("profile") },
                    label = { Text("Profile") },
                    icon = { Icon(Icons.Default.Person, null) }
                )
                NavigationBarItem(
                    selected = selectedTab == "settings",
                    onClick = { selectedTab = "settings"; navController.navigate("settings") },
                    label = { Text("Settings") },
                    icon = { Icon(Icons.Default.Settings, null) }
                )
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("home") { TodoHomeScreen() }
            composable("progress") { ProgressScreen() }
            composable("time") { TimeManagementScreen() }
            composable("profile") { ProfileScreen(role) }
            composable("settings") { SettingsScreen() }
        }
    }
}
