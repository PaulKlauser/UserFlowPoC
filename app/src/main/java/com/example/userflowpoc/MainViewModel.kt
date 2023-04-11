package com.example.userflowpoc

import androidx.lifecycle.ViewModel
import com.example.payment.paymentflow.PaymentFlowState
import com.example.payment.paymentflow.PaymentResultReceiver
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel : ViewModel(), PaymentResultReceiver {
    private val _paymentFlowState = MutableStateFlow<PaymentFlowState?>(null)
    val paymentFlowState = _paymentFlowState.asStateFlow()

    override fun receiveResult(result: PaymentFlowState) {
        _paymentFlowState.value = result
    }
}