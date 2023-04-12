package com.example.payment.paymentflow

import kotlinx.coroutines.flow.MutableStateFlow

class PaymentFlowRepo {
    val paymentFlowState = MutableStateFlow(
        PaymentFlowState(
            "",
            "",
            ""
        )
    )
}