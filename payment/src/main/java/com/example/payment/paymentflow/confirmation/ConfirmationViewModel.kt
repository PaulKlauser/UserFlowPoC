package com.example.payment.paymentflow.confirmation

import androidx.lifecycle.ViewModel
import com.example.payment.paymentflow.PaymentFlowViewModel

class ConfirmationViewModel(flowViewModel: PaymentFlowViewModel) : ViewModel() {

    // Could do additional mapping if desired
    val uiState = flowViewModel.state

}