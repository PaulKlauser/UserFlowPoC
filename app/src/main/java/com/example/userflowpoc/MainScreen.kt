package com.example.userflowpoc

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun MainRoute(onPaymentFlow: () -> Unit) {
    MainScreen(onPaymentFlow = onPaymentFlow)
}

@Composable
fun MainScreen(onPaymentFlow: () -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
    ) {
        Button(onClick = onPaymentFlow) {
            Text(text = "Payment Flow")
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun MainScreenPreview() {
    MainScreen(onPaymentFlow = {})
}