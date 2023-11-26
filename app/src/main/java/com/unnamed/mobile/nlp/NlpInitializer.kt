package com.unnamed.mobile.nlp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.widget.Toast
import com.unnamed.mobile.api.SocketManager
import com.unnamed.mobile.api.TokenEncoder
import com.unnamed.mobile.component.view.MapUiManager
import kotlinx.coroutines.runBlocking

class NlpInitializer {
    fun initSpeechIntent(packageName: String): Intent {
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, packageName)
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "ko-KR")

        return intent
    }
    fun initSpeechListener(applicationContext: Context): RecognitionListener {
        return object : RecognitionListener {
            override fun onReadyForSpeech(p0: Bundle?) {
                Toast.makeText(applicationContext, "음성인식 시작", Toast.LENGTH_SHORT).show();
            }

            override fun onBeginningOfSpeech() {
                //사용자가 말하는 것의 포맷을 제한
                Toast.makeText(applicationContext, "{FORMAT}", Toast.LENGTH_SHORT).show();
            }

            override fun onRmsChanged(p0: Float) {
            }

            override fun onBufferReceived(p0: ByteArray?) {
            }

            override fun onEndOfSpeech() {}

            override fun onError(p0: Int) {
                var message: String = "수행 오류 발생"

                message = when (p0) {
                    SpeechRecognizer.ERROR_AUDIO -> "오디오 에러"
                    SpeechRecognizer.ERROR_CLIENT -> "클라이언트 에러"
                    SpeechRecognizer.ERROR_INSUFFICIENT_PERMISSIONS -> "퍼미션 없음"
                    SpeechRecognizer.ERROR_NETWORK -> "네트워크 에러"
                    SpeechRecognizer.ERROR_NETWORK_TIMEOUT -> "네트웍 타임아웃"
                    SpeechRecognizer.ERROR_NO_MATCH -> "입력된 음성이 없습니다"
                    SpeechRecognizer.ERROR_RECOGNIZER_BUSY -> "RECOGNIZER 가 바쁨"
                    SpeechRecognizer.ERROR_SERVER -> "서버가 이상함"
                    SpeechRecognizer.ERROR_SPEECH_TIMEOUT -> "말하는 시간초과"
                    else -> "알 수 없는 오류임"
                }

                //TODO NoMatch or Recogbusy -> 재시드
                // 나머지에선 연결 끊는 통신 전송
                Toast.makeText(applicationContext, "DEBUG:${MapUiManager.robot.location} --$message", Toast.LENGTH_SHORT).show();
            }

            override fun onResults(p0: Bundle?) {
                //결과값 보여주는 부분
                if (p0 == null) {
                    return
                }
                val matches: ArrayList<String> =
                    p0.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION) as ArrayList<String>
                Toast.makeText(applicationContext, matches.toString(), Toast.LENGTH_SHORT).show();
                runBlocking {
                    SocketManager.sendRequest(TokenEncoder.tokenStaticUpdated(matches))
                }
            }

            override fun onPartialResults(p0: Bundle?) {}

            override fun onEvent(p0: Int, p1: Bundle?) {}

        }
    }
}