package com.example.userflowpoc

import android.content.Context
import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContract
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.payment.paymentflow.PAYMENT_FLOW_RESULT_KEY
import com.example.payment.paymentflow.PaymentFlowActivity
import com.example.payment.paymentflow.PaymentFlowState


private val paymentFlowContract = object : ActivityResultContract<Unit, PaymentFlowState>() {
    override fun createIntent(context: Context, input: Unit): Intent {
        return Intent(context, PaymentFlowActivity::class.java)
    }

    override fun parseResult(resultCode: Int, intent: Intent?): PaymentFlowState {
        return intent?.getParcelableExtra(PAYMENT_FLOW_RESULT_KEY)!!
    }
}

@Composable
fun MainRoute(
    viewModel: MainViewModel
) {
    val launcher = rememberLauncherForActivityResult(
        contract = paymentFlowContract,
        onResult = { result ->
            viewModel.receiveResult(result)
        }
    )
    MainScreen(onPaymentFlow = {
        launcher.launch(Unit)
    }, viewModel.paymentFlowState.collectAsState().value)
}

@Composable
fun MainScreen(
    onPaymentFlow: () -> Unit,
    paymentFlowState: PaymentFlowState?
) {
    Box(
        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Button(onClick = onPaymentFlow) {
                Text(text = "Payment Flow")
            }
            Text(
                text = "Flow State: $paymentFlowState",
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun MainScreenPreview() {
    MainScreen(
        onPaymentFlow = {}, PaymentFlowState(
            name = "name",
            address = "address",
            cardNumber = "cardNumber"
        )
    )
}