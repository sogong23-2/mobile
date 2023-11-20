package com.unnamed.mobile.component.button

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.unnamed.mobile.component.view.MapUiManager
import com.unnamed.mobile.ui.theme.ButtonModifier

@Composable
fun BackButton() {
    Button(
        onClick = {
            MapUiManager.moveRobot(Pair(5,5))
        },
        modifier = ButtonModifier
    ){
        Text(text = "(Back Arrow)")
    }
}