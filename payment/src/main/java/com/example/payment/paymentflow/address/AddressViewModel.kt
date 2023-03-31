package com.example.payment.paymentflow.address

import androidx.lifecycle.ViewModel
import com.example.payment.paymentflow.PaymentFlowViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class AddressViewModel(private val flowViewModel: PaymentFlowViewModel) : ViewModel() {

    private val _uiState =
        MutableStateFlow(flowViewModel.state.value.let { AddressUiState(it.name, it.address) })
    val uiState = _uiState.asStateFlow()

    fun setName(name: String) {
        _uiState.update { it.copy(name = name) }
        flowViewModel.setName(name)
    }

    fun setAddress(address: String) {
        _uiState.update { it.copy(address = address) }
        flowViewModel.setAddress(address)
    }

}