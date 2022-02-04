package mx.kodemia.bookodemiasael.model.libros

import kotlinx.serialization.Serializable

@Serializable
data class DataBook(

    val data: MutableList<Book>

): java.io.Serializable

