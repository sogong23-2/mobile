package com.unnamed.mobile.component.button

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import com.unnamed.mobile.api.SocketManager
import com.unnamed.mobile.ui.theme.buttonModifier

@Composable
fun RobotControlButton() {
    var buttonText by remember { mutableStateOf("Run") }

    Button(
        onClick = {
            if(buttonText == "Run") {
                SocketManager
                buttonText = "Stop"
            } else {
                buttonText = "Run"
            }
        },
        modifier = buttonModifier
    ){
        Text(text = buttonText)
    }
}