package info.vernonengle.voicegpthello.service

import com.google.cloud.translate.Translate
import com.google.cloud.translate.TranslateOptions
import info.vernonengle.voicegpthello.secrets.GCPCredentialsProvider
import org.springframework.stereotype.Service

@Service
class TranslationService(val gcpCredentialsProvider: GCPCredentialsProvider) {
    fun translate(text: String, language: String): TranslateResponse {

        val credentials = gcpCredentialsProvider.getCredentials()
        val translate: Translate = TranslateOptions.newBuilder()
            .setCredentials(credentials).build().service
        val translation = translate.translate(text, Translate.TranslateOption.targetLanguage("es"))
        return TranslateResponse(translation.translatedText)
    }

    data class TranslateRequest(val text: String)
    data class TranslateResponse(val translatedText: String)
}
