package com.example.userflowpoc

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun MainRoute(onPaymentFlow: () -> Unit) {
    MainScreen(onPaymentFlow = onPaymentFlow)
}

@Composable
fun MainScreen(onPaymentFlow: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Cyan),
        contentAlignment = Alignment.Center
    ) {
        val interactionSource = remember { MutableInteractionSource() }
        val isPressed by interactionSource.collectIsPressedAsState()
        val color: Color by animateColorAsState(
            targetValue = if (isPressed) MaterialTheme.colors.primaryVariant else MaterialTheme.colors.primary,
            animationSpec = tween(500)
        )
        val scale: Float by animateFloatAsState(targetValue = if (isPressed) .9f else 1f)
        Button(
            interactionSource = interactionSource,
            onClick = onPaymentFlow,
            elevation = null,
            colors = ButtonDefaults.buttonColors(backgroundColor = color, contentColor = MaterialTheme.colors.onPrimary),
            modifier = Modifier.scale(scale)
        ) {
            Text(text = "Payment Flow")
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun MainScreenPreview() {
    MainScreen(onPaymentFlow = {})
}