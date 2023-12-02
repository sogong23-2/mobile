package com.unnamed.mobile.gui

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun MapViewer(mapSize: Pair<Int, Int>, cellSize: Dp){

    Column {
        for (row in 0 until mapSize.second) {
            Row {
                for (col in 0 until mapSize.first) {
                    MapCell(cellSize)
                }
            }
        }
    }
}

@Composable
fun MapCell(cellSize: Dp) {
    Box(modifier = Modifier.size(cellSize).border(1.dp, Color.Black))
}
