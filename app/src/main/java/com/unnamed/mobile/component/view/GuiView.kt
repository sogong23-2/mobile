package com.unnamed.mobile.component.view
import MapViewer
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import com.unnamed.mobile.component.UploadButton
import com.unnamed.mobile.component.button.BackButton
import com.unnamed.mobile.component.button.NlpButton
import com.unnamed.mobile.component.viewmodel.ComponentViewModel
import com.unnamed.mobile.ui.theme.UnnamedmobileTheme

@Composable
fun GuiView(viewModel: ComponentViewModel) {
    val components = viewModel.data

    UnnamedmobileTheme {
        LazyColumn {
            //Map
            item {
                MapViewer(mapSize = Pair(5, 6),)
            }
            //Components
            items(components.value.size) { index ->
                ComponentViewer(componentView = components.value[index])
            }
            //Buttons
            item { Column {
                UploadButton()
                NlpButton()
                BackButton()
            } }
        }
    }

}
