package com.example.userflowpoc

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.payment.paymentflow.PaymentFlowState
import com.example.payment.paymentflow.destinations.PaymentFlowRouteDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.navigate
import com.ramcosta.composedestinations.result.NavResult
import com.ramcosta.composedestinations.result.OpenResultRecipient
import kotlinx.coroutines.flow.update

@MainNavGraph(start = true)
@Destination
@Composable
fun MainRoute(
    navController: NavController,
    myRepo: MyRepo,
    resultRecipient: OpenResultRecipient<PaymentFlowState>
//    paymentFlowState: PaymentFlowState?
) {
    val viewModel: MainViewModel = viewModel()

    var paymentFlowState: PaymentFlowState? by remember { mutableStateOf(null) }
    resultRecipient.onNavResult {result ->
        when(result) {
            NavResult.Canceled -> {}
            is NavResult.Value -> {
                paymentFlowState = result.value
            }
        }
    }
    
    MainScreen(onPaymentFlow = { navController.navigate(PaymentFlowRouteDestination) },
        paymentFlowState = paymentFlowState,
        counter = viewModel.count.collectAsState().value,
        incrementCounter = { viewModel.count.update { it + 1 } }
    )
}

@Composable
fun MainScreen(
    onPaymentFlow: () -> Unit,
    paymentFlowState: PaymentFlowState?,
    counter: Int,
    incrementCounter: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Row {
                Text("Counter: $counter")
                Button(onClick = incrementCounter) {
                    Text(text = "Increment")
                }
            }
            Button(onClick = onPaymentFlow) {
                Text(text = "Payment Flow")
            }
            Text(
                text = "Flow State: $paymentFlowState",
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun MainScreenPreview() {
    MainScreen(
        onPaymentFlow = {}, PaymentFlowState(
            name = "name",
            address = "address",
            cardNumber = "cardNumber"
        ),
        counter = 0,
        incrementCounter = {}
    )
}