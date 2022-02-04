package mx.kodemia.bookodemiasael.model.libros

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AttributesBook(

    val title: String,
    val slug: String,
    val content: String,
    @SerialName("created-at")
    val createdat: String,
    @SerialName("updated-at")
    val updatedat: String

) : java.io.Serializable