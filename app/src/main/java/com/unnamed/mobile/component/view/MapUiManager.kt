package com.unnamed.mobile.component.view

import com.unnamed.mobile.component.model.*

class MapUiManager {
    val mapSize: Pair<Int, Int> = Pair(5, 6)

    // val components: MutableList<Component> = mutableListOf()
    val components: MutableList<Component> = mutableListOf(
        Blob(Pair(0, 0)),
        Hazard(Pair(1, 1)),
        Blob(Pair(2, 2)),
        Target(Pair(5, 5)),
    )
    var robotStatus: String = "idle"

    //TODO Detail 결정
    fun updateMap(){}
    fun uploadMap(){}
    fun clearMap(){}

}