package com.unnamed.mobile.component.model

data class MapDo(
    val mapSize: Pair<Int, Int>,
    val robot: Pair<Int, Int>,
    val blob: List<Pair<Int, Int>>,
    val hazard: List<Pair<Int, Int>>,
    val targetPoint: List<Pair<Int, Int>>,
    val gray: List<Pair<Int, Int>>
)
