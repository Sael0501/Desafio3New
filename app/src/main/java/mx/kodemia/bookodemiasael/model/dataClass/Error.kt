package mx.kodemia.bookodemiasael.model.dataClass

import kotlinx.serialization.Serializable

@Serializable
data class Error(

    val status: String,
    val title: String,
    val detail: String

): java.io.Serializable
