package com.unnamed.mobile.api

object UserToSystem {
    suspend fun pauseRequest(){
        SocketManager.sendRequest(TokenEncoder.tokenPause())
    }
    suspend fun resumeRequest(){
        SocketManager.sendRequest(TokenEncoder.tokenResume())
    }
    suspend fun updateRequest(statics: List<String>){
        SocketManager.sendRequest(TokenEncoder.tokenStaticUpdated(statics))
    }
    suspend fun initRequest(){
        SocketManager.sendRequest(TokenEncoder.tokenMapInit())
    }
}