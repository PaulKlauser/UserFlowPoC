package com.example.payment.paymentflow

import android.net.Uri
import android.os.Bundle
import androidx.navigation.NavType
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

val PaymentFlowStateType = object : NavType<PaymentFlowState?>(
    isNullableAllowed = true
) {
    override fun get(bundle: Bundle, key: String): PaymentFlowState? {
        return bundle.getParcelable(key) as PaymentFlowState?
    }

    override fun parseValue(value: String): PaymentFlowState {
        return Json.decodeFromString(value)
    }

    override fun put(bundle: Bundle, key: String, value: PaymentFlowState?) {
        bundle.putParcelable(key, value)
    }

    override fun serializeAsValue(value: PaymentFlowState?): String {
        return Uri.encode(Json.encodeToString(value))
    }

}