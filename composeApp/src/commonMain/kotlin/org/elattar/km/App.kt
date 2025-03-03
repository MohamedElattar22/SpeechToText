package org.elattar.km

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.elattar.km.feature.createSpeechToTextManager
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.getKoin

import speechtotext.composeapp.generated.resources.Res
import speechtotext.composeapp.generated.resources.compose_multiplatform

@Composable
@Preview
fun App(
) {
    MaterialTheme {
        VoiceToTextScreen(
            getKoin().get()
        )
//        val speechToTextManager = createSpeechToTextManager()
//        VoiceToTextScreen(speechToText = speechToTextManager)
    }
}