package mx.kodemia.bookodemiasael.model.libros

import kotlinx.serialization.Serializable

@Serializable
data class LinkBook(

    val self: String,
    val related: String = ""

) : java.io.Serializable
