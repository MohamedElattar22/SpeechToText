package org.elattar.km

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.elattar.km.feature.SpeechToText


@Composable
fun VoiceToTextScreen(speechToText: SpeechToText) {
    var recognizedText by remember { mutableStateOf("Your speech will appear here") }
    var isListening by remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(text = recognizedText)
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            if (!isListening) {
                speechToText.startListening(
                    onResult = { result ->
                        recognizedText = result
                    },
                    onError = { error ->
                        recognizedText = "Error: ${error.message}"
                    }
                )
            } else {
                speechToText.stopListening()
            }
            isListening = !isListening
        }) {
            Text(if (isListening) "Stop Listening" else "Start Listening")
        }
    }
}
