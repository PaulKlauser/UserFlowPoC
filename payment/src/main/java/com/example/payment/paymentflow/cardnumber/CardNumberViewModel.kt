package com.example.payment.paymentflow.cardnumber

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.payment.paymentflow.PaymentFlowRepo
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class CardNumberViewModel(private val flowRepo: PaymentFlowRepo) : ViewModel() {

    val uiState = flowRepo.paymentFlowState.map { CardNumberUiState(it.cardNumber) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), CardNumberUiState(""))

    fun setCardNumber(cardNumber: String) {
        flowRepo.paymentFlowState.update { it.copy(cardNumber = cardNumber) }
    }

}