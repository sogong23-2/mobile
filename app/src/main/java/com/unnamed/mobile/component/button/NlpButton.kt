package com.unnamed.mobile.component.button

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.unnamed.mobile.processor.NlpProcessor
import com.unnamed.mobile.ui.theme.ButtonModifier

@Composable
fun NlpButton() {
    val context = LocalContext.current

    Button(
        onClick = { NlpProcessor.startListening(context) },
        modifier = ButtonModifier
    ){
        Text(text = "음성인식")
    }
}