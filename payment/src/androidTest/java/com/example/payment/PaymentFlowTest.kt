package com.example.payment

import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextReplacement
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.payment.paymentflow.PaymentFlowState
import com.example.payment.paymentflow.PaymentResultReceiver
import com.example.payment.paymentflow.paymentFlow
import com.example.payment.paymentflow.paymentFlowRoute
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PaymentFlowTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun flowSmoke() {
        var actualResult: PaymentFlowState? = null
        composeTestRule.setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = paymentFlowRoute) {
                paymentFlow(navController = navController,
                    onCancel = {},
                    onComplete = {},
                    resultReceiverProvider = {
                        object : PaymentResultReceiver {
                            override fun receiveResult(result: PaymentFlowState) {
                                actualResult = result
                            }
                        }
                    })
            }
        }

        composeTestRule.onNodeWithText("Name").performTextReplacement("Paul")
        composeTestRule.onNodeWithText("Address").performTextReplacement("Address")
        composeTestRule.onNodeWithText("Next").performClick()

        composeTestRule.onNodeWithText("Card Number").performTextReplacement("1234")
        composeTestRule.onNodeWithText("Next").performClick()

        composeTestRule.onNodeWithText("Name", substring = true).assertTextEquals("Name: Paul")
        composeTestRule.onNodeWithText("Address", substring = true)
            .assertTextEquals("Address: Address")
        composeTestRule.onNodeWithText("Card Number", substring = true)
            .assertTextEquals("Card Number: 1234")
        composeTestRule.onNodeWithText("Submit").performClick()

        assertEquals(PaymentFlowState("Paul", "Address", "1234"), actualResult)
    }


}