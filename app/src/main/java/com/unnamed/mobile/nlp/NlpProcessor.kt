package com.unnamed.mobile.nlp

import android.content.Context
import android.content.Intent
import android.speech.RecognitionListener
import android.speech.SpeechRecognizer

object NlpProcessor {
    private var intent: Intent? = null
    private var listener: RecognitionListener? = null

    //TODO NotNULL 보장해야한다.
    fun startListening(context: Context) {
        val mRecognizer = SpeechRecognizer.createSpeechRecognizer(context)
        mRecognizer.setRecognitionListener(listener)
        mRecognizer.startListening(intent)
    }

    fun setIntent(intent: Intent) {
        this.intent = intent
    }
    fun setListener(listener: RecognitionListener) {
        this.listener = listener
    }

}