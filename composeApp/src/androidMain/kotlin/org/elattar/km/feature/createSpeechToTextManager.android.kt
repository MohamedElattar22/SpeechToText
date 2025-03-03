package org.elattar.km.feature

import org.koin.java.KoinJavaComponent.getKoin

actual fun createSpeechToTextManager(): SpeechToText {
    return getKoin().get()
}