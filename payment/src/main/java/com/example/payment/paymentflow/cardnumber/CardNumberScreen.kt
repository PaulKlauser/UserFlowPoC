package com.example.payment.paymentflow.cardnumber

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.payment.paymentflow.PaymentFlowViewModel

@Composable
fun CardNumberRoute(onNext: () -> Unit, onCancel: () -> Unit, flowViewModel: PaymentFlowViewModel) {
    val viewModel = viewModel(initializer = { CardNumberViewModel(flowViewModel) })
    val uiState by viewModel.uiState.collectAsState()
    CardNumberScreen(
        cardNumber = uiState.cardNumber,
        onCardNumberChanged = viewModel::setCardNumber,
        onNext = onNext,
        onCancel = onCancel
    )
}

@Composable
fun CardNumberScreen(
    cardNumber: String,
    onCardNumberChanged: (String) -> Unit,
    onNext: () -> Unit,
    onCancel: () -> Unit
) {
    Box {
        Button(onClick = onCancel, modifier = Modifier.padding(8.dp)) {
            Text(text = "Cancel")
        }
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(modifier = Modifier.padding(top = 128.dp)) {
            TextField(
                value = cardNumber,
                onValueChange = onCardNumberChanged,
                placeholder = { Text(text = "Card Number") })
            Button(onClick = onNext, modifier = Modifier.align(Alignment.CenterHorizontally)) {
                Text(text = "Next")
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun CardNumberScreenPreview() {
    CardNumberScreen(
        cardNumber = "1234",
        onCardNumberChanged = {},
        onNext = {},
        onCancel = {}
    )
}