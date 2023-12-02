package com.unnamed.mobile.model

data class MapDo(
    val mapSize: Pair<Int, Int>,
    val robot: Pair<Int, Int>,
    val blob: List<Pair<Int, Int>>,
    val hazard: List<Pair<Int, Int>>,
    val targetPoint: List<Pair<Int, Int>>,
    val gray: List<Pair<Int, Int>>
)

val mapDefault = MapDo(
    mapSize = Pair(5, 5),
    robot = Pair(0, 0),
    blob = listOf(Pair(1, 2)),
    hazard = listOf(Pair(4, 1)),
    targetPoint = listOf(Pair(3, 4), Pair(1,4)),
    gray = listOf(Pair(3, 2))
)