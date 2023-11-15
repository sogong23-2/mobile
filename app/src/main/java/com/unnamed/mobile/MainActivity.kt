package com.unnamed.mobile

import android.Manifest
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer

import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import com.unnamed.mobile.ui.theme.UnnamedmobileTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (Build.VERSION.SDK_INT >= 23) {
            ActivityCompat.requestPermissions(
                this, arrayOf(
                    Manifest.permission.INTERNET,
                    Manifest.permission.RECORD_AUDIO
                ), 1
            )
        }

        val sttIntent: Intent = setSpeechIntent(packageName)
        val speechListener = setSpeechListener(applicationContext)

        fun startListening() {
            val mRecognizer =
                SpeechRecognizer.createSpeechRecognizer(this@MainActivity)
            mRecognizer.setRecognitionListener(speechListener)
            mRecognizer.startListening(sttIntent)
        }
        fun onClickNlp() {
            startListening()
        }

        setContent {
            MainPage()
        }
    }
}

@Composable
fun MainPage() {
    UnnamedmobileTheme {
        Column(modifier = Modifier.fillMaxSize()) {
            Greeting("Android")
            ComposedMap()
        }
    }
}

@Composable
fun RobotView(modifier: Modifier = Modifier.offset(-30.dp, -30.dp)) {
    val image = painterResource(R.drawable.robot)
    Box(modifier = Modifier.size(100.dp)) {
        Image(
            painter = image,
            contentDescription = "robot",
            modifier = modifier
        )
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MainPage()
}


@Composable
fun Map(rows: Int, columns: Int, cellSize: Int) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(rows) { rowIndex ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                for (colIndex in 0 until columns) {
                    if (colIndex == 4 && rowIndex == 4) {
                        Space(cellSize, 1)
                        continue
                    }
                    Space(cellSize, 0)
                }
            }
        }
    }
}

@Composable
fun GridCell(size: Int) {
    Box(
        modifier = Modifier
            .size(size.dp)
            .border(1.dp, Color.Black),
    ) {
        // You can add content to the grid cells here, like icons or labels
    }
}

@Composable
fun Space(size: Int, content: Int) {
    Box(
        modifier = Modifier
            .size(size.dp)
            .border(1.dp, Color.Black),
    ) {
        when(content){
            0 -> GridCell(size = size)
            1 -> FilledGridCell(size = size)
            else -> GridCell(size = size)
        }
    }
}

@Composable
fun FilledGridCell(size: Int) {
    Box(
        modifier = Modifier
            .size(size.dp)
            .clip(shape = MaterialTheme.shapes.small)
            .background(color = Color.White)
            .border(1.dp, Color.Black),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(R.drawable.robot),
            contentDescription = "robot",
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
fun ComposedMap() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Map(rows = 10, columns = 10, cellSize = 60)
        RobotView()
    }
}

fun setSpeechIntent(packageName: String): Intent {
    val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
    intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE,packageName)
    intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "ko-KR")

    return intent
}
fun setSpeechListener(applicationContext: Context): RecognitionListener {
    return object: RecognitionListener {
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