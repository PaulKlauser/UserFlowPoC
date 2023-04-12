package com.example.userflowpoc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.payment.paymentflow.PaymentFlowState
import com.example.payment.paymentflow.destinations.ConfirmationRouteDestination
import com.example.userflowpoc.destinations.MainRouteDestination
import com.example.userflowpoc.ui.theme.UserFlowPocTheme
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.manualcomposablecalls.composable
import com.ramcosta.composedestinations.navigation.dependency
import com.ramcosta.composedestinations.navigation.require
import com.ramcosta.composedestinations.scope.resultRecipient

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UserFlowPocTheme {
                DestinationsNavHost(navGraph = mainGraph,
                    dependenciesContainerBuilder = {
                        dependency(MyRepo())
                    }
                ) {
                    composable(MainRouteDestination) {
                        MainRoute(
                            navController = navController,
                            myRepo = buildDependencies().require(),
                            resultRecipient = resultRecipient<ConfirmationRouteDestination, PaymentFlowState>()
                        )
                    }
                }
            }
        }
    }
}