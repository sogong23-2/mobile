package com.unnamed.mobile.gui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import com.unnamed.mobile.model.model.Dynamic
import com.unnamed.mobile.model.model.Static


@Composable
fun DynamicViewer(dynamic: Dynamic, cellSize: Dp){
    val dynamic: Dynamic = dynamic

    val x: Dp = cellSize * dynamic.location.first
    val y: Dp = cellSize * dynamic.location.second
    val modifier: Modifier = Modifier.offset(x, y)

    Box(Modifier.size(cellSize)) {
        Image(
            painter = painterResource(id = dynamic.src),
            contentDescription = "dynamic",
            modifier = modifier
        )
    }
}

@Composable
fun StaticViewer(static: Static, cellSize: Dp){
    val static: Static = static

    val x: Dp = cellSize * static.location.first
    val y: Dp = cellSize * static.location.second
    System.out.println("dddd ___ static: ${static.location.first}, ${static.location.second}")
    val modifier: Modifier = Modifier.offset(x, y)

    Box(Modifier.size(cellSize)) {
        Image(
            painter = painterResource(id = static.src),
            contentDescription = "dynamic",
            modifier = modifier
        )
    }
}