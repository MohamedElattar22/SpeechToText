package org.elattar.km.di

import org.elattar.km.feature.SpeechToText
import org.elattar.km.feature.SpeechToTextManagerAndroid
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

actual val targetModule: Module = module {
    single<SpeechToText> { SpeechToTextManagerAndroid(androidContext()) }
}
