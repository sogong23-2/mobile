package com.unnamed.mobile.component.view

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun Map2View(mapSize: Pair<Int, Int>){
    val size: Pair<Int, Int> = mapSize

}

@Composable
fun MapView(rows: Int, columns: Int, cellSize: Int) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(rows) { rowIndex ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                for (colIndex in 0 until columns) {
                    if (colIndex == 4 && rowIndex == 4) {
                        Space(cellSize, 1)
                        continue
                    }
                    Space(cellSize, 0)
                }
            }
        }
    }
}

@Composable
fun Space(size: Int, content: Int) {
    Box(
        modifier = Modifier
            .size(size.dp)
            .border(1.dp, Color.Black),
    ) {
        when (content) {
            0 -> GridCell(size = size)
            1 -> FilledGridCell(size = size)
            else -> GridCell(size = size)
        }
    }
}

@Composable
fun GridCell(size: Int) {
    Box(
        modifier = Modifier
            .size(size.dp)
            .border(1.dp, Color.Black),
    ) {

    }
}

@Composable
fun FilledGridCell(size: Int) {

    Box(
        modifier = Modifier
            .size(size.dp)
            .clip(shape = MaterialTheme.shapes.small)
            .background(color = Color.White)
            .border(1.dp, Color.Black),
        contentAlignment = Alignment.Center
    ) {

    }
}