package com.unnamed.mobile.processor

import android.content.Context
import android.content.Intent
import android.speech.RecognitionListener
import android.speech.SpeechRecognizer

class NlpProcessor(intent: Intent, listener: RecognitionListener) {
    private val intent: Intent = intent
    private val listener: RecognitionListener = listener

    fun startListening(context: Context) {
        val mRecognizer = SpeechRecognizer.createSpeechRecognizer(context)
        mRecognizer.setRecognitionListener(listener)
        mRecognizer.startListening(intent)
    }

}