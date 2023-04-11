package com.example.userflowpoc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.payment.paymentflow.paymentFlow
import com.example.payment.paymentflow.paymentFlowRoute
import com.example.userflowpoc.ui.theme.UserFlowPocTheme

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
                            onPaymentFlow = { navController.navigate(route = paymentFlowRoute) },
                            viewModel = viewModel()
                        )
                    }
                    paymentFlow(navController = navController, onCancel = {
                        navController.popBackStack(
                            route = mainRoute, inclusive = false
                        )
                    }, onComplete = {
                        navController.popBackStack(
                            route = mainRoute, inclusive = false
                        )
                    }, resultReceiverProvider = {
                        viewModel<MainViewModel>(remember(it) {
                            navController.getBackStackEntry(mainRoute)
                        })
                    })
                }
            }
        }
    }
}