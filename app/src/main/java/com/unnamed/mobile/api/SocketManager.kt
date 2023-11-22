package com.unnamed.mobile.api

import android.net.http.HttpResponseCache.install
import kotlinx.coroutines.*
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.ServerSocket
import java.net.Socket

private val receivedQueue = mutableListOf<String>()

interface ResponseListener {
    fun onResponseReceived(response: String){
        receivedQueue.add(response)
    }
}

class SocketManager(private val responseListener: ResponseListener) {

    //TODO change init settings
    private val port = 5000
    private val destinationIP = "192.168.1.100"
    private val destinationPort = 6000


    fun serverMode() {
        Thread {
            val serverSocket = ServerSocket(port)
            println("Server started, waiting for connections...")

            while (true) {
                val clientSocket = serverSocket.accept()

                val reader = BufferedReader(InputStreamReader(clientSocket.getInputStream()))
                val writer = OutputStreamWriter(clientSocket.getOutputStream())
                val request = reader.readLine()

                //TODO Handle Request


                writer.close()
                reader.close()
                clientSocket.close()
            }
        }.start()

        // Client behavior: makes a request to another server/device

    }

    fun clientMode(request: String) {
        Thread {
            val clientSocket = Socket(destinationIP, destinationPort)

            val writer = OutputStreamWriter(clientSocket.getOutputStream())
            writer.write(request)
            writer.flush()

            val reader = BufferedReader(InputStreamReader(clientSocket.getInputStream()))
            val response = reader.readLine()
            responseListener.onResponseReceived(response)

            writer.close()
            reader.close()
            clientSocket.close()
        }.start()
    }

}

