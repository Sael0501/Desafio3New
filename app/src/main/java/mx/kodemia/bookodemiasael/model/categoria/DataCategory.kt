package mx.kodemia.bookodemiasael.model.categoria

import kotlinx.serialization.Serializable

@Serializable
data class DataCategory(
    val data : MutableList<Category>
): java.io.Serializable
