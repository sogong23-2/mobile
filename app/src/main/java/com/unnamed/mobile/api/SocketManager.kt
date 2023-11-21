package com.unnamed.mobile.api

class SocketManager {

}

import io.ktor.client.*
import io.ktor.client.features.websocket.*
import io.ktor.http.cio.websocket.*
import kotlinx.coroutines.*

suspend fun main() {
    val client = HttpClient {
        install(WebSockets)
    }

    val webSocketSession = client.webSocketSession {
        url("wss://echo.websocket.org")
        // Handle incoming messages
        while (true) {
            val message = incoming.receive() as? Frame.Text ?: continue
            println("Received: ${message.readText()}")
        }
    }

    // Send a message
    webSocketSession.send("Hello, WebSocket!")

    // Keep the connection open for some time (you can use your own logic)
    delay(5000)

    // Close the WebSocket connection
    webSocketSession.close()
    client.close()
}