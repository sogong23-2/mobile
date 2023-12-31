package com.unnamed.mobile.api

import com.unnamed.mobile.model.model.*
import com.unnamed.mobile.gui.MapUiManager

object TokenDecoder {
    fun destinationMoved(tokens: List<String>): Pair<Int, Int> {
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

    fun parseCmd(data: String): String {
        return data.substring(0, data.indexOf('/'))
    }

    fun parseToToken(data: String): List<String> {
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