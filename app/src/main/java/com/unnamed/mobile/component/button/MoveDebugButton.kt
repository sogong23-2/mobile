package com.unnamed.mobile.component.button

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.unnamed.mobile.component.view.MapUiManager
import com.unnamed.mobile.ui.theme.buttonModifier
import kotlinx.coroutines.*
import kotlin.math.roundToInt

@Composable
fun MoveDebugButton() {
    Button(
        onClick = {
//            CoroutineScope(GlobalScope.coroutineContext).launch {
//                withContext(Dispatchers.Main) {
//                    MapUiManager.moveRobot(Pair(5, 5))
//                }
//            }
            CoroutineScope(GlobalScope.coroutineContext).launch {
                withContext(Dispatchers.Main) {
                    var now = MapUiManager.robot.location
                    var nowInt = Pair(now.first.roundToInt(), now.second.roundToInt())
                    MapUiManager.moveRobot(Pair(nowInt.first-1, nowInt.second))
                }
            }
        },
        modifier = buttonModifier
    ){
        Text(text = "이동 - (5,5)")
    }
}