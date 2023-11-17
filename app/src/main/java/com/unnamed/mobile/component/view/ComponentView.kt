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

open class ComponentView{
    open val location: Pair<Int, Int> = Pair(0, 0)
    open val src: Int = 0
}

open class DynamicView(): ComponentView() {
    open fun move() {}
}

class RobotView(location: Pair<Int, Int>): DynamicView(){
    override val src = R.drawable.robot
    override var location = location
    override fun move() {}
}

//TODO change R.drawable.robot
class BlobView(location: Pair<Int, Int>): ComponentView() {
    override val src = R.drawable.robot
    override val location = location
}
class HardCodedView(location: Pair<Int, Int>): ComponentView() {
    override val src = R.drawable.robot
    override val location = location
}
class TargetView(location: Pair<Int, Int>): ComponentView() {
    override val src = R.drawable.robot
    override val location = location
}