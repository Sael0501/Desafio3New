package mx.kodemia.bookodemiasael.model.categoria

import kotlinx.serialization.Serializable


@Serializable
data class CatLink (

    val self: String,
    val related: String = ""

): java.io.Serializable


