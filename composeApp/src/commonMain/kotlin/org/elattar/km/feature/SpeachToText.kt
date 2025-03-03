package org.elattar.km.feature

// commonMain
interface SpeechToText {
    /**
     * Starts listening for voice input.
     *
     * @param onResult Callback invoked with recognized text.
     * @param onError Callback invoked on error.
     */
    fun startListening(onResult: (String) -> Unit, onError: (Throwable) -> Unit)

    /** Stops listening for voice input. */
    fun stopListening()
}
