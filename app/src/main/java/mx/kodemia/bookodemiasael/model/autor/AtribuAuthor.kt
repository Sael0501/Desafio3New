package mx.kodemia.bookodemiasael.model.autor

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AtribuAuthor(
    val name: String,
    @SerialName("created-at")
    val createdAt: String,
    @SerialName("updated-at")
    val updatedAt: String
): java.io.Serializable

