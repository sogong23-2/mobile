package com.unnamed.mobile.api

import kotlinx.coroutines.*
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStream
import java.io.OutputStreamWriter
import java.net.ServerSocket
import java.net.Socket
import java.nio.charset.StandardCharsets
import kotlin.coroutines.suspendCoroutine

class SocketInstance(private val responseListener: ResponseListener) {

    //TODO change init settings
    private val port = 5001
    private val destinationIP = "192.168.0.110"
    private val destinationPort = 5002


    fun serverMode() {
        Thread {
            val serverSocket = ServerSocket(port)
            System.out.println("-----------------Server started, waiting for connections..."
                    + serverSocket.inetAddress.hostAddress)

            while (true) {
                val clientSocket = serverSocket.accept()

                val reader = BufferedReader(InputStreamReader(clientSocket.getInputStream()))
                val writer = OutputStreamWriter(clientSocket.getOutputStream())
                val request = reader.readLine()

                CoroutineScope(GlobalScope.coroutineContext).launch {
                    withContext(Dispatchers.Main) {
                        SocketHandler.apiResolver(request)
                    }
                }

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
            val outputStream: OutputStream = clientSocket.getOutputStream()
            val writer = OutputStreamWriter(outputStream, StandardCharsets.UTF_8)
            writer.write(request)
            writer.flush()

            val reader = BufferedReader(InputStreamReader(clientSocket.getInputStream()))
            val response = reader.readLine()
            if (response != "ACK") {
                System.out.println("Error: $response")
                //TODO handle error
            }

            writer.close()
            reader.close()
            clientSocket.close()
        }.start()
    }

}

