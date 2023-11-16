package com.unnamed.mobile.processor

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.widget.Toast

class NlpProcessor {

    fun setSpeechIntent(packageName: String): Intent {
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, packageName)
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "ko-KR")

        return intent
    }

    fun setSpeechListener(applicationContext: Context): RecognitionListener {
        return object : RecognitionListener {
            override fun onReadyForSpeech(p0: Bundle?) {
                Toast.makeText(applicationContext, "음성인식 시작", Toast.LENGTH_SHORT).show();
            }

            override fun onBeginningOfSpeech() {
                //사용자가 말하는 것의 포맷을 제한
                Toast.makeText(applicationContext, "FORMAT", Toast.LENGTH_SHORT).show();
            }

            override fun onRmsChanged(p0: Float) {
            }

            override fun onBufferReceived(p0: ByteArray?) {
            }

            override fun onEndOfSpeech() {
            }

            override fun onError(p0: Int) {
                var message: String = "수행 오류 발생"

                message = when (p0) {
                    SpeechRecognizer.ERROR_AUDIO -> "오디오 에러"
                    SpeechRecognizer.ERROR_CLIENT -> "클라이언트 에러"
                    SpeechRecognizer.ERROR_INSUFFICIENT_PERMISSIONS -> "퍼미션 없음"
                    SpeechRecognizer.ERROR_NETWORK -> "네트워크 에러"
                    SpeechRecognizer.ERROR_NETWORK_TIMEOUT -> "네트웍 타임아웃"
                    SpeechRecognizer.ERROR_NO_MATCH -> "찾을 수 없음"
                    SpeechRecognizer.ERROR_RECOGNIZER_BUSY -> "RECOGNIZER 가 바쁨"
                    SpeechRecognizer.ERROR_SERVER -> "서버가 이상함"
                    SpeechRecognizer.ERROR_SPEECH_TIMEOUT -> "말하는 시간초과"
                    else -> "알 수 없는 오류임"
                }

                Toast.makeText(applicationContext, "에러 발생 : $message", Toast.LENGTH_SHORT).show();
            }

            override fun onResults(p0: Bundle?) {
                //결과값 보여주는 부분
                if (p0 == null) {
                    return
                }
                val matches: ArrayList<String> =
                    p0.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION) as ArrayList<String>
            }

            override fun onPartialResults(p0: Bundle?) {}

            override fun onEvent(p0: Int, p1: Bundle?) {}

        }
    }
}