package com.unnamed.mobile.api

import android.net.http.HttpResponseCache.install
import kotlinx.coroutines.*
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.ObjectOutputStream
import java.io.OutputStreamWriter
import java.net.ServerSocket
import java.net.Socket

class SocketManager {
    //TODO change init settings
    private val port = 5000
    private val destinationIP = "192.168.1.100"
    private val destinationPort = 6000

    fun connect() {
        Thread {
            val serverSocket = ServerSocket(port)
            println("Server started, waiting for connections...")

            while (true) {
                val clientSocket = serverSocket.accept()

                println("Connection established with ${clientSocket.remoteSocketAddress}")

                // Handle incoming client requests
                val reader = BufferedReader(InputStreamReader(clientSocket.getInputStream()))
                val writer = OutputStreamWriter(clientSocket.getOutputStream())

                val request = reader.readLine()
                println("Received from client: $request")

                // Respond to the client
                val response = "Hello from Android server!"
                writer.write(response)
                writer.flush()

                // Close the resources for this client connection
                writer.close()
                reader.close()
                clientSocket.close()
            }
        }.start()

        // Client behavior: makes a request to another server/device
        Thread {
            val clientSocket = Socket(destinationIP, destinationPort)

            // Send a request to the server/device
            val writer = OutputStreamWriter(clientSocket.getOutputStream())
            val request = "Hello from Android client!"
            writer.write(request)
            writer.flush()

            // Receive response from the server/device
            val reader = BufferedReader(InputStreamReader(clientSocket.getInputStream()))
            val response = reader.readLine()
            println("Received from server/device: $response")

            // Close the resources for this client connection
            writer.close()
            reader.close()
            clientSocket.close()
        }.start()
    }
}

