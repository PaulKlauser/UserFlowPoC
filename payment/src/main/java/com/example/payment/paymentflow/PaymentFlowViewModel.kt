package com.example.payment.paymentflow

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class PaymentFlowViewModel : ViewModel() {

    private val _state =
        MutableStateFlow(PaymentFlowState(name = "", address = "", cardNumber = ""))
    val state = _state.asStateFlow()

    fun setName(name: String) {
        _state.update { it.copy(name = name) }
    }

    fun setAddress(address: String) {
        _state.update { it.copy(address = address) }
    }

    fun setCardNumber(cardNumber: String) {
        _state.update { it.copy(cardNumber = cardNumber) }
    }

}