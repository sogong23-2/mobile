package com.unnamed.mobile

import android.Manifest
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import com.unnamed.mobile.processor.NlpInitializer
import com.unnamed.mobile.component.view.GuiView
import com.unnamed.mobile.component.view.MapUiManager
import com.unnamed.mobile.component.viewmodel.ComponentViewModel
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

        MapUiManager.uploadMap()
        print("done?")

        val nlpInitializer = NlpInitializer()
        NlpProcessor.setIntent(nlpInitializer.initSpeechIntent(packageName = packageName))
        NlpProcessor.setListener(nlpInitializer.initSpeechListener(applicationContext))


        fun onClickNlp() {
            NlpProcessor.startListening(this@MainActivity)
        }

        setContent {
            GuiView(viewModel = MapUiManager.componentViewModel) { recreate() }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    GuiView(viewModel = MapUiManager.componentViewModel) {recreate()}
}

fun recreate() {}

