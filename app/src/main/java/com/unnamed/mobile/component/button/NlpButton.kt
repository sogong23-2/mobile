package com.unnamed.mobile.component.button

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Mic
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.unnamed.mobile.api.SocketManager
import com.unnamed.mobile.api.TokenEncoder
import com.unnamed.mobile.component.view.MapUiManager
import com.unnamed.mobile.processor.NlpProcessor
import com.unnamed.mobile.ui.theme.buttonModifier
import com.unnamed.mobile.ui.theme.iconModifier
import kotlinx.coroutines.*
import kotlin.math.roundToInt

@Composable
fun NlpButton() {
    val context = LocalContext.current

    Button(
        onClick = {
            CoroutineScope(GlobalScope.coroutineContext).launch {
                withContext(Dispatchers.Main) {
                    SocketManager.sendRequest(TokenEncoder.tokenPause())
                    NlpProcessor.startListening(context)
                }
            }

        },
        modifier = buttonModifier
    ) {
        Row {
            Icon(
                imageVector = Icons.Default.Mic,
                contentDescription = "Mic",
                iconModifier
            )
        }
    }
}