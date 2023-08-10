package info.vernonengle.voicegpthello.controller

import info.vernonengle.voicegpthello.service.TranslationService
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
class Translator(val translationService: TranslationService) {

    @GetMapping("/translate")
    fun translate(@RequestParam language: String): ResponseEntity<String> {
        val translation = translationService.translate("hello world", language).translatedText
        return ResponseEntity.ok(translation)
    }
}