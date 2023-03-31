package com.example.payment.paymentflow.cardnumber

import androidx.lifecycle.ViewModel
import com.example.payment.paymentflow.PaymentFlowViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CardNumberViewModel(private val flowViewModel: PaymentFlowViewModel) : ViewModel() {

    private val _uiState = MutableStateFlow(flowViewModel.state.value.let { CardNumberUiState(it.cardNumber) })
    val uiState = _uiState.asStateFlow()

    fun setCardNumber(cardNumber: String) {
        _uiState.update { it.copy(cardNumber = cardNumber) }
        flowViewModel.setCardNumber(cardNumber)
    }

}