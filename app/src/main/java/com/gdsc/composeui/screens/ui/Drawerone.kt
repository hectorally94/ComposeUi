package com.gdsc.composeui.screens.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun Drawerone(
    modifier: Modifier = Modifier
) {
    Box(modifier) {
        Text(text = "Drawerone")
    }
}

@Preview(name = "Drawerone")
@Composable
private fun PreviewDrawerone() {
    Drawerone()
}