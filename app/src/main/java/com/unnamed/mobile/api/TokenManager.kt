package com.unnamed.mobile.api

import com.unnamed.mobile.component.model.*
import com.unnamed.mobile.component.view.MapUiManager

object TokenManager {
    suspend fun apiResolver(data: String) {
        val cmd = parseCmd(data)
        val tokens = parseToToken(data)

        when (cmd) {
            "MRG" -> {
                MapUiManager.moveRobot(destinationMoved(tokens))
            }
            "UMG" -> {
                MapUiManager.updateMap(staticUpdated(tokens))
            }
            else -> {
                println("invalid command")
            }

        }
    }

    private fun destinationMoved(tokens: List<String>): Pair<Int, Int> {
        val token = tokens[0]
        val type = token[0]
        val payload = token.substring(1, token.length - 1)
        val coordination = payload.split(",")

        val coordPair = Pair(coordination[0].toInt(), coordination[1].toInt())

        (type == 'r').let {
            return coordPair
        }
        val robotLocation = MapUiManager.getRobotLocation()
        return Pair(robotLocation.first.toInt(), robotLocation.second.toInt())
    }

    fun staticUpdated(tokens: List<String>): List<Static> {
        val statics = mutableListOf<Static>()

        for (token in tokens) {
            val type = token[0]
            val payload = token.substring(1, token.length - 1)
            val coordination = payload.split(",")
            val coordPair = Pair(coordination[0].toInt(), coordination[1].toInt())

            when (type) {
                'b' -> {
                    statics.add(Blob(coordPair))
                }
                'h' -> {
                    statics.add(Hazard(coordPair))
                }
                't' -> {
                    statics.add(TargetPoint(coordPair))
                }
                else -> {
                    println("invalid command")
                }
            }
        }

        return statics
    }

    fun uploadMap(stream: String): MapDo {
        var mapSize: Pair<Int, Int> = Pair(0, 0)
        var robot: Pair<Int, Int> = Pair(0, 0)
        var blobs: MutableList<Pair<Int, Int>> = mutableListOf()
        var hazards: MutableList<Pair<Int, Int>> = mutableListOf()
        var targetPoints: MutableList<Pair<Int, Int>> = mutableListOf()

        for (token in parseToToken(stream)) {

            val type = token[0]
            val payload = token.substring(1, token.length - 1)
            val coordination = payload.split(",")

            when (type) {
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

    private fun parseCmd(data: String): String {
        return data.substring(0, data.indexOf('/'))
    }

    private fun parseToToken(data: String): List<String> {
        val tokens = mutableListOf<String>()
        var currentIndex = 4

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
}