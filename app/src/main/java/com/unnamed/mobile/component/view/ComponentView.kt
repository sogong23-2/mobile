package com.unnamed.mobile.component.view

import com.unnamed.mobile.R

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
    override val src = R.drawable.blob
    override val location = location
}
class HazardView(location: Pair<Int, Int>): ComponentView() {
    override val src = R.drawable.hazard
    override val location = location
}
class TargetView(location: Pair<Int, Int>): ComponentView() {
    override val src = R.drawable.target
    override val location = location
}