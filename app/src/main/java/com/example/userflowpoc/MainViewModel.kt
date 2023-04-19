package com.example.userflowpoc

import androidx.lifecycle.ViewModel
import com.example.payment.paymentflow.PaymentFlowState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel : ViewModel() {
    private val _paymentFlowState = MutableStateFlow<PaymentFlowState?>(null)
    val paymentFlowState = _paymentFlowState.asStateFlow()

    fun receiveResult(result: PaymentFlowState) {
        _paymentFlowState.value = result
    }
}