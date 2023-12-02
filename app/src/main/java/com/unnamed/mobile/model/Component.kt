package com.unnamed.mobile.model.model

import com.unnamed.mobile.R

open class Component{
    open val src: Int = 0
}

open class Dynamic(location: Pair<Float, Float>): Component(){
    var location: Pair<Float, Float> = location
    fun move(next: Pair<Float, Float>){
        location = next
    }
}

open class Static(location: Pair<Int, Int>): Component(){
    val location: Pair<Int, Int> = location
}

class Robot(location: Pair<Float, Float>): Dynamic(location){
    override val src = R.drawable.robot
    var trace: MutableList<Pair<Float, Float>> = mutableListOf()
    fun moveTo(next: Pair<Float, Float>){
        trace.add(next)
        move(next)
    }
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

class Gray(location: Pair<Int, Int>): Static(location){
    override val src = R.drawable.gray
}
