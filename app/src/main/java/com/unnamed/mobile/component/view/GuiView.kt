package com.unnamed.mobile.component.view

import HandInUploadButton
import MapViewer
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.unnamed.mobile.component.UploadButton
import com.unnamed.mobile.component.button.BackButton
import com.unnamed.mobile.component.button.MoveDebugButton
import com.unnamed.mobile.component.button.NlpButton
import com.unnamed.mobile.component.button.RobotControlButton
import com.unnamed.mobile.component.viewmodel.ComponentViewModel
import com.unnamed.mobile.ui.theme.UnnamedmobileTheme

@Composable
fun GuiView(viewModel: ComponentViewModel, recreateActivity: () -> Unit) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp

    //TODO 디테일 스펙 수정
    val cellSize: Dp = screenWidth / (viewModel.getMapSize().first)

    UnnamedmobileTheme {
        LazyColumn() {
            item {
                Box {
                    // MapViewer as the background
                    MapViewer(mapSize = viewModel.getMapSize(), cellSize = cellSize)

                    // Components stacked on top of the MapViewer
                    viewModel.getStatics().forEach { component ->
                        StaticViewer(static = component, cellSize = cellSize)
                    }
                    viewModel.getDynamics().forEach { component ->
                        DynamicViewer(dynamic = component, cellSize = cellSize)
                    }
                }
            }
            item {
                Column(
                    Modifier
                        .fillMaxSize()
                        .padding(top = 45.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Row {
                        UploadButton(recreateActivity)
                        Box(modifier = Modifier.size(30.dp))
                        HandInUploadButton(onSubmit = {MapUiManager.initMap(it)})
                        Box(modifier = Modifier.size(30.dp))
                        NlpButton(viewModel)
                    }
                    Box(modifier = Modifier.size(20.dp))
                    Row {
                        MoveDebugButton()
                        Box(modifier = Modifier.size(30.dp))
                        RobotControlButton(viewModel)
                    }
                }
            }
        }
    }
}



