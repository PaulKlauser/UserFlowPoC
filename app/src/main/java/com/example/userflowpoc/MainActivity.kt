package com.example.userflowpoc

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.payment.paymentflow.PaymentFlowState
import com.example.payment.paymentflow.PaymentFlowStateType
import com.example.payment.paymentflow.paymentFlow
import com.example.payment.paymentflow.paymentFlowRoute
import com.example.userflowpoc.ui.theme.UserFlowPocTheme
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

private fun mainRoute(arg: PaymentFlowState? = null): String {
    val argString = if (arg != null) {
        Uri.encode(Json.encodeToString(arg))
    } else {
        "{$ARG_KEY}"
    }
    return "main?$ARG_KEY=$argString"
}

private const val ARG_KEY = "arg"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UserFlowPocTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = mainRoute()) {
                    composable(
                        route = mainRoute(), arguments = listOf(
                            navArgument(ARG_KEY) {
                                type = PaymentFlowStateType
                                defaultValue = null
                                nullable = true
                            }
                        )) {
                        MainRoute(
                            onPaymentFlow = { navController.navigate(route = paymentFlowRoute) },
                            paymentFlowState = it.arguments?.getParcelable(ARG_KEY)
                        )
                    }
                    paymentFlow(navController = navController, onCancel = {
                        navController.popBackStack(
                            route = mainRoute(), inclusive = false
                        )
                    }, onComplete = {
                        navController.popBackStack(
                            route = mainRoute(), inclusive = false
                        )
                        navController.navigate(route = mainRoute(it)) {
                            launchSingleTop = true
                        }
                    })
                }
            }
        }
    }
}