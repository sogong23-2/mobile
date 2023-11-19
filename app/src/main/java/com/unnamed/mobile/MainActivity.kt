package com.unnamed.mobile

import android.Manifest
import android.os.Build
import android.os.Bundle
import android.speech.SpeechRecognizer
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
import com.unnamed.mobile.processor.NlpInitializer
import com.unnamed.mobile.component.UploadButton
import com.unnamed.mobile.component.button.BackButton
import com.unnamed.mobile.component.button.NlpButton
import com.unnamed.mobile.component.view.*
import com.unnamed.mobile.processor.NlpProcessor
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

        val nlpInitializer = NlpInitializer()
        NlpProcessor.setIntent(nlpInitializer.initSpeechIntent(packageName = packageName))
        NlpProcessor.setListener(nlpInitializer.initSpeechListener(applicationContext))


        fun onClickNlp() {
            NlpProcessor.startListening(this@MainActivity)
        }

        setContent {
            MainPage()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MainPage()
}

@Composable
fun MainPage() {
    UnnamedmobileTheme {
        Column(modifier = Modifier.fillMaxSize()) {
            NlpButton()
        }
//        Column(modifier = Modifier.fillMaxSize()) {
//            Greeting("Android")
//            ComposedMap()
//
//            Greeting(name = "Why not?")
//        }
    }
}
