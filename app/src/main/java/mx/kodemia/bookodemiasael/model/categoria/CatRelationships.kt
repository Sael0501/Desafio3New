package mx.kodemia.bookodemiasael.model.categoria

import kotlinx.serialization.Serializable

@Serializable
data class CatRelationships(

    val books: CatBooks

): java.io.Serializable
