package mx.kodemia.bookodemiasael.model.libros

import kotlinx.serialization.Serializable

@Serializable
data class RelationshipsBook(

    val authors: AuthorBook,
    val categories: CategoriesBook

) : java.io.Serializable