package com.example.userflowpoc

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

const val MAIN = "main"
const val PAYMENT_FLOW_RESULT_KEY = "paymentFlowResult"
const val MAIN_ROUTE = "$MAIN?$PAYMENT_FLOW_RESULT_KEY={$PAYMENT_FLOW_RESULT_KEY}"

private fun mainDestination(arg: PaymentFlowState? = null): String {
    return if (arg != null) {
        "$MAIN?$PAYMENT_FLOW_RESULT_KEY=${PaymentFlowStateType.serializeAsValue(arg)}"
    } else {
        MAIN
    }
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UserFlowPocTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = MAIN_ROUTE) {
                    composable(
                        route = MAIN_ROUTE, arguments = listOf(
                            navArgument(PAYMENT_FLOW_RESULT_KEY) {
                                type = PaymentFlowStateType
                                defaultValue = null
                                nullable = true
                            }
                        )) {
                        MainRoute(
                            onPaymentFlow = { navController.navigate(route = paymentFlowRoute) },
                            paymentFlowState = it.arguments?.getParcelable(PAYMENT_FLOW_RESULT_KEY)
                        )
                    }
                    paymentFlow(navController = navController, onCancel = {
                        navController.popBackStack(
                            route = MAIN_ROUTE, inclusive = false
                        )
                    }, onComplete = {
                        navController.popBackStack(
                            route = MAIN_ROUTE, inclusive = false
                        )
                        navController.navigate(route = mainDestination(it)) {
                            launchSingleTop = true
                        }
                    })
                }
            }
        }
    }
}