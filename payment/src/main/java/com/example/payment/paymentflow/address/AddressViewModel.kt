package com.example.payment.paymentflow.address

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.payment.paymentflow.PaymentFlowRepo
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class AddressViewModel(private val paymentRepo: PaymentFlowRepo) : ViewModel() {

    val uiState = paymentRepo.paymentFlowState.map {
        AddressUiState(
            name = it.name,
            address = it.address
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), AddressUiState("", ""))

    fun setName(name: String) {
        paymentRepo.paymentFlowState.update { it.copy(name = name) }
    }

    fun setAddress(address: String) {
        paymentRepo.paymentFlowState.update { it.copy(address = address) }
    }

}