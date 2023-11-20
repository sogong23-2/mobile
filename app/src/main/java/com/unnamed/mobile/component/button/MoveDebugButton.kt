package com.unnamed.mobile.component.button

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.unnamed.mobile.component.view.MapUiManager
import com.unnamed.mobile.ui.theme.ButtonModifier
import kotlinx.coroutines.*

@Composable
fun MoveDebugButton() {
    Button(
        onClick = {
            CoroutineScope(GlobalScope.coroutineContext).launch {
                withContext(Dispatchers.Main) {
                    MapUiManager.moveRobot(Pair(5, 5))
                }
            }
        },
        modifier = ButtonModifier
    ){
        Text(text = "이동")
    }
}