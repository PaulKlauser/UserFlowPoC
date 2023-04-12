package com.example.payment.paymentflow.address

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
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
import androidx.navigation.NavController
import com.example.payment.paymentflow.PaymentFlowRepo
import com.example.payment.paymentflow.destinations.CardNumberRouteDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.navigate

//@PaymentNavGraph(start = true)
@Destination(start = true)
@Composable
fun AddressRoute(navController: NavController,
                 paymentFlowRepo: PaymentFlowRepo) {
    val viewModel = viewModel(initializer = { AddressViewModel(paymentFlowRepo) })
    val uiState by viewModel.uiState.collectAsState()
    AddressScreen(
        name = uiState.name,
        onNameChange = viewModel::setName,
        address = uiState.address,
        onAddressChanged = viewModel::setAddress,
        onNext = { navController.navigate(CardNumberRouteDestination) }
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