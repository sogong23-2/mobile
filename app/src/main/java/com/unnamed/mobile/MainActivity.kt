package com.unnamed.mobile

import android.Manifest
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import com.unnamed.mobile.api.SocketManager
import com.unnamed.mobile.nlp.NlpInitializer
import com.unnamed.mobile.gui.GuiView
import com.unnamed.mobile.gui.MapUiManager
import com.unnamed.mobile.nlp.NlpProcessor

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

        SocketManager.openServer()
        MapUiManager.uploadMap()

        val nlpInitializer = NlpInitializer()
        NlpProcessor.setIntent(nlpInitializer.initSpeechIntent(packageName = packageName))
        NlpProcessor.setListener(nlpInitializer.initSpeechListener(applicationContext))

        setContent {
            GuiView(viewModel = MapUiManager.viewModel, applicationContext) { recreate() }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
//    GuiView(viewModel = MapUiManager.componentViewModel) {recreate()}
}

fun recreate() {}

