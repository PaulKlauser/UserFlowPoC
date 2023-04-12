package com.example.userflowpoc

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.payment.paymentflow.PaymentFlowState
import kotlinx.coroutines.flow.update

@Composable
fun MainRoute(
    onPaymentFlow: () -> Unit,
    paymentFlowState: PaymentFlowState?
) {
    val viewModel: MainViewModel = viewModel()
    MainScreen(onPaymentFlow = onPaymentFlow,
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