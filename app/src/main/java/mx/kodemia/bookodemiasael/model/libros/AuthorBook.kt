package mx.kodemia.bookodemiasael.model.libros

import kotlinx.serialization.Serializable

@Serializable
data class AuthorBook(

    val links: LinkBook

) : java.io.Serializable