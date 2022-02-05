package mx.kodemia.bookodemiasael.model.libros

import kotlinx.serialization.Serializable
import mx.kodemia.bookodemiasael.R

@Serializable
data class Book (

    val type: String,
    val id: String,
    val attributes: AttributesBook,
    val relationships: RelationshipsBook,
    val links: LinkBook,
    val book : Int = R.drawable.libro_1

): java.io.Serializable