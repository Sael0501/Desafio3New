package mx.kodemia.bookodemiasael.model.categoria

import kotlinx.serialization.Serializable

@Serializable
data class Category(

    val type: String,
    val id: String,
    val attributes: CatAttributes,
    val relationships: CatRelationships,
    val links: CatLink

): java.io.Serializable
