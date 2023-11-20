package com.unnamed.mobile.component.view

import MapViewer
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.unnamed.mobile.component.UploadButton
import com.unnamed.mobile.component.button.BackButton
import com.unnamed.mobile.component.button.NlpButton
import com.unnamed.mobile.component.model.Dynamic
import com.unnamed.mobile.component.model.Static
import com.unnamed.mobile.component.viewmodel.ComponentViewModel
import com.unnamed.mobile.ui.theme.UnnamedmobileTheme

@Composable
fun GuiView(viewModel: ComponentViewModel) {
    val mapSize = MapUiManager.mapSize

    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp

    //TODO 디테일 스펙 수정
    val cellSize: Dp = screenWidth / (mapSize.first)

    UnnamedmobileTheme {
        LazyColumn() {
            item {
                Box {
                    // MapViewer as the background
                    MapViewer(mapSize = mapSize, cellSize = cellSize)

                    // Components stacked on top of the MapViewer
                    viewModel.getComponents().forEach { component ->
                        if(component is Dynamic){
                            DynamicViewer(dynamic = component, cellSize = cellSize)
                        }
                        else if(component is Static){
                            StaticViewer(static = component, cellSize = cellSize)
                        }
                    }
                }
            }
            item {
                Column() {
                    UploadButton()
                    NlpButton()
                    BackButton()
                }
            }
        }
    }
}



