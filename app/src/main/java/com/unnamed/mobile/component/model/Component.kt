package com.unnamed.mobile.component.model

open class Component{}

open class Dynamic(location: Pair<Float, Float>): Component(){
    var location: Pair<Float, Float> = location
}

open class Static(location: Pair<Int, Int>): Component(){
    val location: Pair<Int, Int> = location
}

class Robot(location: Pair<Float, Float>): Dynamic(location){
    var trace: MutableList<Pair<Int, Int>> = mutableListOf()
}
class Blob(location: Pair<Int, Int>): Static(location){

}
class Hazard(location: Pair<Int, Int>): Static(location){

}
class TargetPoint(location: Pair<Int, Int>): Static(location){

}
