package com.unnamed.mobile.component.view

import com.unnamed.mobile.R

open class ComponentView{
    open val src: Int = 0
}

open class DynamicView(): ComponentView() {
    open val location: Pair<Float, Float> = Pair(0F, 0F)
    open fun move() {}
}
open class StaticView(): ComponentView() {
    open val location: Pair<Int, Int> = Pair(0, 0)
}

class RobotView(location: Pair<Float, Float>): DynamicView(){
    override val src = R.drawable.robot
    override var location = location

    override fun move() {
        for(i in 0..100) {
            location = Pair(location.first + 1, location.second + 1)
        }
    }
}

//TODO change R.drawable.robot
class BlobView(location: Pair<Int, Int>): StaticView() {
    override val src = R.drawable.blob
    override val location = location
}
class HazardView(location: Pair<Int, Int>): StaticView() {
    override val src = R.drawable.hazard
    override val location = location
}
class TargetView(location: Pair<Int, Int>): StaticView() {
    override val src = R.drawable.target
    override val location = location
}