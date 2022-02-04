package mx.kodemia.bookodemiasael.model.categoria

import kotlinx.serialization.Serializable

@Serializable
data class CatAttributes(
    val name: String,
    val slug: String

): java.io.Serializable
