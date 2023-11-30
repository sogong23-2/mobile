package com.unnamed.mobile.api

private val receivedQueue = mutableListOf<String>()

interface ResponseListener {
    fun onResponseReceived(response: String) {
        receivedQueue.add(response)
    }
}