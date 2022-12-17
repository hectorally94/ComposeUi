package com.gdsc.composeui.screens.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun DashBoardthree(
    modifier: Modifier = Modifier
) {
    Box(modifier) {
        Text(text = "DashBoardthree")
    }
}

@Preview(name = "DashBoardthree")
@Composable
private fun PreviewDashBoardthree() {
    DashBoardthree()
}