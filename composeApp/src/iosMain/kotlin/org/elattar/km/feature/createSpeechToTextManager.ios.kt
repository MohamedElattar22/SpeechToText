package org.elattar.km.feature

import org.koin.mp.KoinPlatform.getKoin

actual fun createSpeechToTextManager(): SpeechToText {
    return getKoin().get()
}