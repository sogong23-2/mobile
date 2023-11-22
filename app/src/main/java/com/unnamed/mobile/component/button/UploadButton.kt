package com.unnamed.mobile.component

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Upload
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.unnamed.mobile.api.StreamManager
import com.unnamed.mobile.component.view.MapUiManager
import com.unnamed.mobile.ui.theme.buttonModifier
import com.unnamed.mobile.ui.theme.iconModifier


@Composable
fun UploadButton(recreateActivity: () -> Unit) {
    val context = LocalContext.current

    Button(
        onClick = {
            MapUiManager.initMap(
                StreamManager.streamResolver("m7,6/r0,0/b1,5/b2,2/h1,1/t4,5/")
            )
            recreateActivity()
        },
        modifier = buttonModifier
    ) {
        Row {
            Icon(
                imageVector = Icons.Default.Upload,
                contentDescription = "Mic",
                iconModifier
            )
        }
    }
}

private fun rebuildActivity(activity: Activity) {
    activity.recreate()
}