package com.unnamed.mobile.api
import kotlin.coroutines.suspendCoroutine

object SocketManager {

    suspend fun sendRequest(data: String) = suspendCoroutine<Unit> { continuation ->

        val responseListener = object : ResponseListener {
            override fun onResponseReceived(response: String) {
                println("response: $response")
            }
        }
        val socket = SocketInstance(responseListener)
        socket.clientMode(data)

        continuation.resumeWith(Result.success(Unit))
    }

    fun openServer() {
        val responseListener = object : ResponseListener {
            override fun onResponseReceived(response: String) {
                println("response: $response")
            }
        }
        val socket = SocketInstance(responseListener)
        socket.serverMode()
    }
}