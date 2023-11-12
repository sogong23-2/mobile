package com.unnamed.mobile.component

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.unnamed.mobile.ui.theme.ButtonModifier


@Composable
fun UploadButton(){
    Button(
        onClick = { /*TODO*/ },
        modifier = ButtonModifier
    ){
        Text(text = "Upload")
    }
}