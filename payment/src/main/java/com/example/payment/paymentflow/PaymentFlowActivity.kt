package com.example.payment.paymentflow

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.base.theme.UserFlowPocTheme

const val PAYMENT_FLOW_RESULT_KEY = "paymentFlowResult"

class PaymentFlowActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UserFlowPocTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = paymentFlowRoute) {
                    paymentFlow(navController = navController,
                        onCancel = { finish() },
                        onComplete = {
                            setResult(RESULT_OK, Intent().apply {
                                putExtra(PAYMENT_FLOW_RESULT_KEY, it)
                            })
                            finish()
                        })
                }
            }
        }
    }
}