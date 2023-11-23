package com.unnamed.mobile.component

import android.app.AlertDialog
import android.content.Context
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Upload
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.unnamed.mobile.api.TokenManager
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
    val map: MapDo = TokenManager.uploadMap("ULM/m7,6/r0,0/b1,5/b2,2/h1,1/t4,5/")

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
    val map: MapDo = TokenManager.uploadMap("m7,6/r0,0/b1,5/b2,2/h1,1/t4,5/")

    val builder = AlertDialog.Builder(context)
    builder.setTitle("지도 입력 방법")
    builder.setMessage("옵션")

    builder.setPositiveButton("자동입력") { _, _ ->
        MapUiManager.autoInit()
    }

    builder.setNegativeButton("직접입력") { _, _ ->
        MapUiManager.initMap(map)
    }

    builder.show()
}