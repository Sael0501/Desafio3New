package mx.kodemia.bookodemiasael.model.autor

import kotlinx.serialization.Serializable
import mx.kodemia.bookodemiasael.model.categoria.CatLink
import mx.kodemia.bookodemiasael.model.categoria.CatRelationships

@Serializable
data class AuthorsData(
    val type: String,
    val id: String,
    val attributes: AtribuAuthor,
    val relationships: CatRelationships,
    val links: CatLink
): java.io.Serializable

