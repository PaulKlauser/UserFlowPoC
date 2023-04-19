package com.example.userflowpoc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.base.theme.UserFlowPocTheme

private const val mainRoute = "main"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UserFlowPocTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = mainRoute) {
                    composable(mainRoute) {
                        MainRoute(
                            viewModel = viewModel()
                        )
                    }
                }
            }
        }
    }
}