package com.example.payment.paymentflow

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class PaymentFlowState(
    val name: String,
    val address: String,
    val cardNumber: String
) : Parcelable
