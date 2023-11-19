package com.unnamed.mobile.component.model

open class Component(location: Pair<Int, Int>) {
    open val location: Pair<Int, Int> = location
}
open class Dynamic(location: Pair<Int, Int>): Component(location) {
    override var location: Pair<Int, Int> = location
}

class Robot(location: Pair<Int, Int>): Dynamic(location){
    var trace: MutableList<Pair<Int, Int>> = mutableListOf()
}
class Blob(location: Pair<Int, Int>): Component(location){

}
class Hazard(location: Pair<Int, Int>): Component(location){

}
class Target(location: Pair<Int, Int>): Component(location){

}
