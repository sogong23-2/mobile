package com.unnamed.mobile.component.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun ComponentViewer(componentView: ComponentView) {
    val componentView: ComponentView = componentView

    //TODO componentView.location에 따라서 Box 위치 조정
    val modifier: Modifier = Modifier.offset(-30.dp, -30.dp)

    Box(modifier = Modifier.size(100.dp)) {
        Image(
            painter = painterResource(id =componentView.src),
            contentDescription = "robot",
            modifier = modifier
        )
    }
}