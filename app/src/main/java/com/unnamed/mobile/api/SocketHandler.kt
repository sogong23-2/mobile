package com.unnamed.mobile.api

import com.unnamed.mobile.gui.MapUiManager

object SocketHandler {
    suspend fun apiResolver(data: String) {
        val cmd = TokenDecoder.parseCmd(data)
        val tokens = TokenDecoder.parseToToken(data)

        when (cmd) {
            "MRG" -> {
                MapUiManager.moveRobot(TokenDecoder.destinationMoved(tokens))
            }
            "UMG" -> {
                MapUiManager.updateMap(TokenDecoder.staticUpdated(tokens))
            }
            else -> {
                println("invalid command")
            }

        }
    }
}