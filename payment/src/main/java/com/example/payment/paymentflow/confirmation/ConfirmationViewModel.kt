package com.example.payment.paymentflow.confirmation

import androidx.lifecycle.ViewModel
import com.example.payment.paymentflow.PaymentFlowRepo

class ConfirmationViewModel(flowRepo: PaymentFlowRepo) : ViewModel() {

    // Could do additional mapping if desired
    val uiState = flowRepo.paymentFlowState

}