package com.unnamed.mobile.component

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Upload
import androidx.compose.runtime.Composable
import com.unnamed.mobile.component.model.MapDo
import com.unnamed.mobile.component.view.MapUiManager
import com.unnamed.mobile.component.viewmodel.ComponentViewModel
import com.unnamed.mobile.ui.theme.buttonModifier
import com.unnamed.mobile.ui.theme.iconModifier


@Composable
fun UploadButton(recreateActivity: () -> Unit) {
    Button(
        onClick = {
            MapUiManager.initMap(MapDo(
                mapSize = Pair(7, 6),
                robot = Pair(0, 0),
                blob = listOf(Pair(1, 5), Pair(2, 2)),
                hazard = listOf(Pair(1, 1)),
                targetPoint = listOf(Pair(4, 5))
            ))
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