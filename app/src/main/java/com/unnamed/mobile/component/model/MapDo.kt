package com.unnamed.mobile.component.model

data class MapDo(
    val mapSize: Pair<Int, Int>,
    val robot: Pair<Int, Int>,
    val blob: List<Pair<Int, Int>>,
    val hazard: List<Pair<Int, Int>>,
    val targetPoint: List<Pair<Int, Int>>,
)

fun streamResolver(stream: String): MapDo{

    var mapSize: Pair<Int, Int> = Pair(0, 0)
    var robot: Pair<Int, Int> = Pair(0, 0)
    var blobs: MutableList<Pair<Int, Int>> = mutableListOf()
    var hazards: MutableList<Pair<Int, Int>> = mutableListOf()
    var targetPoints: MutableList<Pair<Int, Int>> = mutableListOf()

    for(token in parseToToken(stream)){

        val type = token[0]
        val payload = token.substring(1, token.length - 1)
        val coordination = payload.split(",")

        when(type){
            'm' -> {
                mapSize = Pair(coordination[0].toInt(), coordination[1].toInt())
            }
            'r' -> {
                robot = Pair(coordination[0].toInt(), coordination[1].toInt())
            }
            'b' -> {
                blobs.add(Pair(coordination[0].toInt(), coordination[1].toInt()))
            }
            'h' -> {
                hazards.add(Pair(coordination[0].toInt(), coordination[1].toInt()))
            }
            't' -> {
                targetPoints.add(Pair(coordination[0].toInt(), coordination[1].toInt()))
            }
        }
    }

    return MapDo(mapSize, robot, blobs, hazards, targetPoints)
}

fun parseToToken(data: String): List<String>{
        val tokens = mutableListOf<String>()
        var currentIndex = 0

        while (currentIndex < data.length) {
            val nextIndex = data.indexOf('/', currentIndex)
            if (nextIndex != -1) {
                val substring = data.substring(currentIndex, nextIndex + 1)
                tokens.add(substring)
                currentIndex = nextIndex + 1
            } else {
                break
            }
        }

        return tokens
}
