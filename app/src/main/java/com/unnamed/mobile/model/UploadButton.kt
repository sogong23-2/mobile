package com.unnamed.mobile.model

import android.app.AlertDialog
import android.content.Context
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CloudUpload
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.unnamed.mobile.api.*
import com.unnamed.mobile.gui.MapUiManager
import com.unnamed.mobile.ui.theme.buttonModifier
import com.unnamed.mobile.ui.theme.iconModifier
import kotlinx.coroutines.runBlocking

@Composable
fun UploadButton(onQuit: () -> Unit) {
    val context = LocalContext.current

    Button(
        onClick = { showDialog(context) },
        modifier = buttonModifier
    ) {
        Row {
            Icon(
                imageVector = Icons.Default.CloudUpload,
                contentDescription = "Upload",
                iconModifier
            )
        }
    }
}

fun showDialog(context: Context) {
    val builder = AlertDialog.Builder(context)
    builder.setTitle("지도 기본값으로 업로드")

    builder.setNegativeButton("취소") { dialog, _ ->
        dialog.dismiss()
    }

    builder.setPositiveButton("업로드") { _, _ ->
        MapUiManager.autoInit()
        runBlocking {
            UserToSystem.initRequest()
        }
    }

    builder.show()
}