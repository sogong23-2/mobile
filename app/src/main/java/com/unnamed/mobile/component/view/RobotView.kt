package com.unnamed.mobile.component.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.unnamed.mobile.R

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