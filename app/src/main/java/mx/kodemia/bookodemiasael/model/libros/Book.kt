package mx.kodemia.bookodemiasael.model.libros

import kotlinx.serialization.Serializable

@Serializable
data class Book (

    val type: String,
    val id: String,
    val attributes: AttributesBook,
    val relationships: RelationshipsBook,
    val links: LinkBook

): java.io.Serializable