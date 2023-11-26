package com.unnamed.mobile.component.button

import androidx.compose.foundation.layout.Row
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Mic
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.unnamed.mobile.api.SocketManager
import com.unnamed.mobile.component.viewmodel.ComponentViewModel
import com.unnamed.mobile.nlp.NlpProcessor
import com.unnamed.mobile.ui.theme.buttonModifier
import com.unnamed.mobile.ui.theme.iconModifier
import kotlinx.coroutines.*

@Composable
fun NlpButton(viewModel: ComponentViewModel) {
    val context = LocalContext.current

    Button(
        onClick = {
            CoroutineScope(GlobalScope.coroutineContext).launch {
                withContext(Dispatchers.Main) {
                    viewModel.pauseRobot()
                    runBlocking {
                        SocketManager.pauseRequest()
                    }
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