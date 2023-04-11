package com.example.payment.paymentflow

interface PaymentResultReceiver {
    fun receiveResult(result: PaymentFlowState)
}