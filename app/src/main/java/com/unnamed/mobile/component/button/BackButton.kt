package com.unnamed.mobile.component.button

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.unnamed.mobile.ui.theme.buttonModifier
import kotlinx.coroutines.*

@Composable
fun BackButton() {
    Button(
        onClick = {},
        modifier = buttonModifier
    ){
        Text(text = "나가기")
    }
}