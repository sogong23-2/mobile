package com.unnamed.mobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.unnamed.mobile.component.UploadButton
import com.unnamed.mobile.ui.theme.UnnamedmobileTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainPage()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MainPage()
}

@Composable
fun MainPage() {
    UnnamedmobileTheme {
        Column(modifier = Modifier.fillMaxSize()) {
            Greeting("Android")
            ComposedMap()
            UploadButton()
        }
    }
}

@Composable
fun RobotView(modifier: Modifier = Modifier.offset(-30.dp, -30.dp)) {
    val image = painterResource(R.drawable.robot)
    Box(modifier = Modifier.size(100.dp)) {
        Image(
            painter = image,
            contentDescription = "robot",
            modifier = modifier
        )
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Composable
fun Map(rows: Int, columns: Int, cellSize: Int) {
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
fun GridCell(size: Int) {
    Box(
        modifier = Modifier
            .size(size.dp)
            .border(1.dp, Color.Black),
    ) {
        // You can add content to the grid cells here, like icons or labels
    }
}

@Composable
fun Space(size: Int, content: Int) {
    Box(
        modifier = Modifier
            .size(size.dp)
            .border(1.dp, Color.Black),
    ) {
        when(content){
            0 -> GridCell(size = size)
            1 -> FilledGridCell(size = size)
            else -> GridCell(size = size)
        }
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
        Image(
            painter = painterResource(R.drawable.robot),
            contentDescription = "robot",
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
fun ComposedMap() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Map(rows = 10, columns = 10, cellSize = 60)
        RobotView()
    }
}