package com.unnamed.mobile.component.view

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun ComponentViewer(componentView: ComponentView, cellSize: Dp) {
    val componentView: ComponentView = componentView

    val x: Dp = cellSize * componentView.location.first
    val y: Dp = cellSize * componentView.location.second
    val modifier: Modifier = Modifier.offset(x, y)

    Box(Modifier.size(cellSize)) {
        Image(
            painter = painterResource(id = componentView.src),
            contentDescription = "component",
            modifier = modifier
        )
    }
}

//@Composable
//fun DynamicViewer(componentView: ComponentView, cellSize: Dp) {
//    val x: Dp = cellSize * componentView.location.first
//    val y: Dp = cellSize * componentView.location.second
//
//    var xOffset by remember { mutableStateOf(x) }
//    var yOffset by remember { mutableStateOf(y) }
//
//    val coroutineScope = rememberCoroutineScope()
//    val animatedOffset = remember { Animatable(0.0F) }
//
//    LaunchedEffect(Unit) {
//        coroutineScope.launch {
//            animatedOffset.animateTo(
//                cellSize.value, animationSpec = tween(
//                    durationMillis = 1000,
//                    delayMillis = 0
//                )
//            ) {}
//            //TODO change this to only move the one direction
//            xOffset += animatedOffset.value.dp
//            yOffset += animatedOffset.value.dp
//        }
//    }
//
//    Box(
//        modifier = Modifier
//            .size(cellSize)
//            .offset(x = xOffset, y = yOffset)
//    ) {
//        Image(
//            painter = painterResource(id = componentView.src),
//            contentDescription = "component",
//            modifier = Modifier.fillMaxSize()
//        )
//    }
//}