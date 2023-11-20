package com.unnamed.mobile.component

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.unnamed.mobile.component.view.MapUiManager
import com.unnamed.mobile.ui.theme.ButtonModifier
import kotlinx.coroutines.*


@Composable
fun UploadButton(){
    Button(
        onClick = {},
        modifier = ButtonModifier
    ){
        Text(text = "Upload")
    }
}