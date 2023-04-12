package com.example.payment.paymentflow.confirmation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.payment.paymentflow.PaymentFlowRepo
import com.example.payment.paymentflow.PaymentFlowState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.result.ResultBackNavigator

@Composable
@Destination
fun ConfirmationRoute(
    paymentFlowRepo: PaymentFlowRepo,
    resultBackNavigator: ResultBackNavigator<PaymentFlowState>
) {
    val viewModel = viewModel(initializer = { ConfirmationViewModel(paymentFlowRepo) })
    val uiState by viewModel.uiState.collectAsState()
    ConfirmationScreen(
        name = uiState.name,
        address = uiState.address,
        cardNumber = uiState.cardNumber,
        // So this only allows navigating back one screen, I can't pop the whole flow
        onSubmit = { resultBackNavigator.navigateBack(it) }
    )
}

@Composable
fun ConfirmationScreen(
    name: String,
    address: String,
    cardNumber: String,
    onSubmit: (PaymentFlowState) -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(modifier = Modifier.padding(top = 128.dp)) {
            Text(text = "Name: $name")
            Text(text = "Address: $address")
            Text(text = "Card Number: $cardNumber")
            Button(onClick = { onSubmit(PaymentFlowState(name, address, cardNumber)) }) {
                Text(text = "Submit")
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun ConfirmationScreenPreview() {
    ConfirmationScreen(name = "P. Sherman", address = "42 Wallaby Way", cardNumber = "1234", {})
}