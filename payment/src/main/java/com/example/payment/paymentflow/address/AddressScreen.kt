package com.example.payment.paymentflow.address

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.payment.paymentflow.PaymentFlowViewModel

@Composable
fun AddressRoute(onNext: () -> Unit, flowViewModel: PaymentFlowViewModel) {
    val viewModel = viewModel(initializer = { AddressViewModel(flowViewModel) })
    val uiState by viewModel.uiState.collectAsState()
    AddressScreen(
        name = uiState.name,
        onNameChange = viewModel::setName,
        address = uiState.address,
        onAddressChanged = viewModel::setAddress,
        onNext = onNext
    )
}

@Composable
fun AddressScreen(
    name: String,
    onNameChange: (String) -> Unit,
    address: String,
    onAddressChanged: (String) -> Unit,
    onNext: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(modifier = Modifier.padding(top = 128.dp)) {
            TextField(
                value = name,
                onValueChange = onNameChange,
                placeholder = { Text(text = "Name") })
            Spacer(modifier = Modifier.height(16.dp))
            TextField(
                value = address,
                onValueChange = onAddressChanged,
                placeholder = { Text(text = "Address") })
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = onNext, modifier = Modifier.align(Alignment.CenterHorizontally)) {
                Text(text = "Next")
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AddressScreenPreview() {
    AddressScreen(
        name = "P. Sherman",
        onNameChange = {},
        address = "42 Wallaby Way",
        onAddressChanged = {},
        onNext = {}
    )
}