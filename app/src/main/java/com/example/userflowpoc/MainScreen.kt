package com.example.userflowpoc

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.payment.paymentflow.PaymentFlowState

@Composable
fun MainRoute(
    onPaymentFlow: () -> Unit,
    paymentFlowState: PaymentFlowState?
) {
    MainScreen(onPaymentFlow = onPaymentFlow, paymentFlowState)
}

@Composable
fun MainScreen(
    onPaymentFlow: () -> Unit,
    paymentFlowState: PaymentFlowState?
) {
    Box(
        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
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
        )
    )
}