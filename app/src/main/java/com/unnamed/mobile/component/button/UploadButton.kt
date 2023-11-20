package com.unnamed.mobile.component

import androidx.compose.foundation.layout.Row
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Upload
import androidx.compose.runtime.Composable
import com.unnamed.mobile.ui.theme.buttonModifier
import com.unnamed.mobile.ui.theme.iconModifier


@Composable
fun UploadButton(){
    Button(
        onClick = {},
        modifier = buttonModifier
    ){
        Row {
            Icon(
                imageVector = Icons.Default.Upload,
                contentDescription = "Mic",
                iconModifier
            )
        }
    }
}