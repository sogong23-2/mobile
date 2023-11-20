package com.unnamed.mobile.component.model

import com.unnamed.mobile.R

open class Component{
    open val src: Int = 0
}

open class Dynamic(location: Pair<Float, Float>): Component(){
    var location: Pair<Float, Float> = location
}

open class Static(location: Pair<Int, Int>): Component(){
    val location: Pair<Int, Int> = location
}

class Robot(location: Pair<Float, Float>): Dynamic(location){
    override val src = R.drawable.robot
    var trace: MutableList<Pair<Int, Int>> = mutableListOf()
}
class Blob(location: Pair<Int, Int>): Static(location){
    override val src = R.drawable.blob

}
class Hazard(location: Pair<Int, Int>): Static(location){
    override val src = R.drawable.hazard

}
class TargetPoint(location: Pair<Int, Int>): Static(location){
    override val src = R.drawable.target

}
