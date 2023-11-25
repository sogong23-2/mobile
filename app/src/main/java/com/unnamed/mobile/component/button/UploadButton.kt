package com.unnamed.mobile.component

import android.app.AlertDialog
import android.content.Context
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Autorenew
import androidx.compose.material.icons.filled.CloudUpload
import androidx.compose.material.icons.filled.Upload
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.unnamed.mobile.api.*
import com.unnamed.mobile.component.model.MapDo
import com.unnamed.mobile.component.view.MapUiManager
import com.unnamed.mobile.ui.theme.buttonModifier
import com.unnamed.mobile.ui.theme.iconModifier

@Composable
fun UploadButton(onQuit: () -> Unit) {
    val context = LocalContext.current
    val map: MapDo = TokenDecoder.uploadMap("ULM/m7,6/r0,0/b1,5/b2,2/h1,1/t4,5/")

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
    //TODO remove
    val map: MapDo = TokenDecoder.uploadMap("UML/m7,6/r0,0/b1,5/b2,2/h1,1/t4,5/")

    val builder = AlertDialog.Builder(context)
    builder.setTitle("지도 기본값으로 업로드")

    builder.setNegativeButton("취소") { dialog, _ ->
        dialog.dismiss()
    }

    builder.setPositiveButton("업로드") { _, _ ->
        MapUiManager.autoInit()
        SocketManager.sendRequest(TokenEncoder.tokenMapInit())
    }

    builder.show()
}