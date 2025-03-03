package org.elattar.km

import androidx.compose.ui.window.ComposeUIViewController
import org.elattar.km.di.initializeKoin

fun MainViewController() = ComposeUIViewController (
    configure = {
        initializeKoin()
    }
){  App()}