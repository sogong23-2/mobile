package com.unnamed.mobile.component

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Upload
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.unnamed.mobile.api.StreamManager
import com.unnamed.mobile.component.model.MapDo
import com.unnamed.mobile.component.view.MapUiManager
import com.unnamed.mobile.ui.theme.buttonModifier
import com.unnamed.mobile.ui.theme.iconModifier


//@Composable
//fun UploadButton(recreateActivity: () -> Unit) {
//    //TODO remove
//    val map: MapDo = StreamManager.streamResolver("m7,6/r0,0/b1,5/b2,2/h1,1/t4,5/")
//
//    Button(
//        onClick = {
//            showDialog(context, dialogLauncher)
//            //
//            inputToInitMap(map)
//            recreateActivity()
//        },
//        modifier = buttonModifier
//    ) {
//        Row {
//            Icon(
//                imageVector = Icons.Default.Upload,
//                contentDescription = "Mic",
//                iconModifier
//            )
//        }
//    }
//}

@Composable
fun UploadButton(onQuit: () -> Unit) {
    val context = LocalContext.current
    val map: MapDo = StreamManager.streamResolver("m7,6/r0,0/b1,5/b2,2/h1,1/t4,5/")

    Button(
        onClick = { showDialog(context) },
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

fun showDialog(context: Context) {
    //TODO remove
    val map: MapDo = StreamManager.streamResolver("m7,6/r0,0/b1,5/b2,2/h1,1/t4,5/")

    val builder = AlertDialog.Builder(context)
    builder.setTitle("Select Option")
    builder.setMessage("Choose an option:")

    builder.setPositiveButton("Auto") { _, _ ->
        MapUiManager.autoInit()
    }

    builder.setNegativeButton("Hand") { _, _ ->
        MapUiManager.initMap(map)
    }

    builder.show()
}