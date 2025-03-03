package org.elattar.km.feature

import kotlinx.cinterop.ExperimentalForeignApi
import platform.AVFAudio.AVAudioEngine
import platform.AVFAudio.AVAudioSession
import platform.AVFAudio.AVAudioSessionCategoryRecord
import platform.AVFAudio.AVAudioSessionModeMeasurement
import platform.AVFAudio.setActive
import platform.Speech.SFSpeechAudioBufferRecognitionRequest
import platform.Speech.SFSpeechRecognitionResult
import platform.Speech.SFSpeechRecognitionTask
import platform.Speech.SFSpeechRecognizer
import platform.Speech.SFSpeechRecognizerAuthorizationStatus

class SpeechToTextManagerIos : SpeechToText {
    private val speechRecognizer = SFSpeechRecognizer()
    private val audioEngine = AVAudioEngine()
    private var recognitionTask: SFSpeechRecognitionTask? = null

    @OptIn(ExperimentalForeignApi::class)
    override fun startListening(onResult: (String) -> Unit, onError: (Throwable) -> Unit) {
        // Request authorization if needed
        SFSpeechRecognizer.requestAuthorization { authStatus ->
            if (authStatus != SFSpeechRecognizerAuthorizationStatus.SFSpeechRecognizerAuthorizationStatusAuthorized) {
                onError(Exception("Speech recognition not authorized"))
                return@requestAuthorization
            }

            // Configure audio session
            val audioSession = AVAudioSession.sharedInstance()
            audioSession.setCategory(AVAudioSessionCategoryRecord, error = null)
            audioSession.setMode(AVAudioSessionModeMeasurement, error = null)
            audioSession.setActive(true, error = null)

            // Create a recognition request and start the task
            val recognitionRequest = SFSpeechAudioBufferRecognitionRequest().apply {
                shouldReportPartialResults = true
            }

            recognitionTask = speechRecognizer.recognitionTaskWithRequest(recognitionRequest) { result: SFSpeechRecognitionResult?, error ->
                if (error != null) {
                    onError(Exception(error.localizedDescription))
                    return@recognitionTaskWithRequest
                }
                result?.let {
                    // Get the best transcription result
                    val bestResult = it.bestTranscription.formattedString ?: ""
                    onResult(bestResult)
                }
            }

            // Install an audio tap to feed the recognition request
            val inputNode = audioEngine.inputNode
            val recordingFormat = inputNode.outputFormatForBus(0u)
            inputNode.installTapOnBus(0u, bufferSize = 1024u, format = recordingFormat) { buffer, _ ->
                recognitionRequest.appendAudioPCMBuffer(buffer!!)
            }

            audioEngine.prepare()
            audioEngine.startAndReturnError(null)
        }
    }

    override fun stopListening() {
        audioEngine.stop()
        audioEngine.inputNode.removeTapOnBus(0u)
        recognitionTask?.cancel()
        recognitionTask = null
    }
}
