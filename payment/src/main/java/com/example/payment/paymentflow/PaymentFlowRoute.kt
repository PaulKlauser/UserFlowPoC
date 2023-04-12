package com.example.payment.paymentflow

import androidx.compose.runtime.Composable
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.dependency

@Destination
@Composable
fun PaymentFlowRoute() {
    val paymentFlowRepo = PaymentFlowRepo()
    DestinationsNavHost(navGraph = paymentGraph, dependenciesContainerBuilder = {
        dependency(paymentFlowRepo)
    })
}