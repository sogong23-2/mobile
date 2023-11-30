package com.unnamed.mobile.component.button

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.unnamed.mobile.api.SocketManager
import com.unnamed.mobile.api.UserToSystem
import com.unnamed.mobile.component.viewmodel.ComponentViewModel
import com.unnamed.mobile.ui.theme.buttonModifier
import kotlinx.coroutines.runBlocking

@Composable
fun RobotControlButton(viewModel: ComponentViewModel) {

    Button(
        onClick = {
            if(viewModel.getRobotStatus() == "Running") {
                runBlocking {
                    UserToSystem.pauseRequest()
                }
                viewModel.pauseRobot()
            } else {
                //음성입력을 받아오는 동안은 반려.
                runBlocking {
                    UserToSystem.resumeRequest()
                }
                viewModel.resumeRobot()
            }
        },
        modifier = buttonModifier
    ){
        Text(text = viewModel.getRobotStatus())
    }
}