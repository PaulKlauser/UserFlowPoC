package com.example.payment.paymentflow

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.payment.paymentflow.address.AddressRoute
import com.example.payment.paymentflow.cardnumber.CardNumberRoute
import com.example.payment.paymentflow.confirmation.ConfirmationRoute

const val paymentFlowRoute = "payment"
private const val addressRoute = "address"
private const val cardNumberRoute = "cardNumber"
private const val confirmationRoute = "confirmation"

//TODO: PK - Passing in navController?
fun NavGraphBuilder.paymentFlow(navController: NavController, onCancel: () -> Unit) {
    navigation(startDestination = addressRoute, route = paymentFlowRoute) {
        composable(addressRoute) { backStackEntry ->
            AddressRoute(
                onNext = { navController.navigate(route = cardNumberRoute) },
                navController.getFlowViewModel(
                    backStackEntry = backStackEntry
                )
            )
        }
        composable(cardNumberRoute) { backStackEntry ->
            CardNumberRoute(
                onNext = { navController.navigate(route = confirmationRoute) },
                onCancel = onCancel,
                navController.getFlowViewModel(
                    backStackEntry = backStackEntry
                )
            )
        }
        composable(confirmationRoute) { backStackEntry ->
            ConfirmationRoute(
                navController.getFlowViewModel(
                    backStackEntry = backStackEntry
                )
            )
        }
    }
}

@Composable
private fun NavController.getFlowViewModel(
    backStackEntry: NavBackStackEntry
): PaymentFlowViewModel {
    val parentEntry = remember(backStackEntry) {
        getBackStackEntry(paymentFlowRoute)
    }
    return viewModel(parentEntry)
}