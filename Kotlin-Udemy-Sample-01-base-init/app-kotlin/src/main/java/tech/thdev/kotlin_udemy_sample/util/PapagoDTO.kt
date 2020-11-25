package tech.thdev.kotlin_udemy_sample.util

data class PapagoDTO(val message: Message?) {
    data class Message(val result: Result?) {
        data class Result(val translatedText: String)
    }
}